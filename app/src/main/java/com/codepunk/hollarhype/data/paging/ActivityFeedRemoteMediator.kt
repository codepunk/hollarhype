package com.codepunk.hollarhype.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeedEntry
import com.codepunk.hollarhype.data.local.relation.LocalActivityWithDetails
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.mapper.toRepositoryException
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.LocalDateTime
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ActivityFeedRemoteMediator(
    private val deviceDateTime: LocalDateTime,
    private val webservice: HollarhypeWebservice,
    private val database: HollarhypeDatabase
) : RemoteMediator<Int, LocalActivityWithDetails>() {

    // region Variables

    private val activityDao = database.activityDao()
    private val activityFeedDao = database.activityFeedDao()

    // endregion Variables

    // region Methods

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalActivityWithDetails>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val activityFeed = getActivityFeedClosestToCurrentPosition(state)
                activityFeed?.nextPage?.minus(1) ?: 1
            }
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val activityFeed = getActivityFeedForLastItem(state)
                when {
                    activityFeed == null -> return MediatorResult.Error(
                        IllegalStateException("Result is empty")
                    )
                    activityFeed.lastPage ->
                        return MediatorResult.Success(endOfPaginationReached = true)
                    else -> activityFeed.nextPage
                }
            }
        }

        return try {
            webservice.activityFeed(
                deviceDateTime = deviceDateTime,
                page = page
            ).run {
                body.fold(
                    ifLeft = { remoteError ->
                        MediatorResult.Error(remoteError.toRepositoryException(code, message))
                    },
                    ifRight = { activityFeed ->
                        // Save
                        database.withTransaction {
                            if (loadType == LoadType.REFRESH) {
                                activityFeedDao.clearActivityFeed()
                                activityDao.clearActivities()
                            }
                            activityDao.insertActivitiesWithDetails(
                                activityFeed.activities.map { it.toLocal() }
                            )
                            activityFeedDao.insertActivityFeed(
                                // TODO NEXT Simplify? Better mapper?
                                activityFeed.activities.map { activity ->
                                    LocalActivityFeedEntry(
                                        activityId = activity.id,
                                        delta = activityFeed.delta,
                                        nextPage = activityFeed.nextPage,
                                        lastPage = activityFeed.lastPage
                                    )
                                }
                            )
                        }

                        MediatorResult.Success(endOfPaginationReached = activityFeed.lastPage)
                    }
                )
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getActivityFeedClosestToCurrentPosition(
        state: PagingState<Int, LocalActivityWithDetails>
    ): LocalActivityFeedEntry? = state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.activity?.id?.let { activityId ->
            database.withTransaction {
                database.activityFeedDao().getActivityFeedByActivityId(activityId).firstOrNull()
            }
        }
    }

    private suspend fun getActivityFeedForLastItem(
        state: PagingState<Int, LocalActivityWithDetails>
    ): LocalActivityFeedEntry? = state.lastItemOrNull()?.let {
        database.withTransaction {
            database.activityFeedDao().getActivityFeedByActivityId(
                it.activity.id
            ).firstOrNull()
        }
    }

    // endregion Methods

}
