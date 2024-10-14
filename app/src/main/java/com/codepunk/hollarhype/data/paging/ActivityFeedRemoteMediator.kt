package com.codepunk.hollarhype.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeedPage
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
                val activityFeed = getRemoteKeysClosestToCurrentPosition(state)
                activityFeed?.page ?: 1
            }

            LoadType.PREPEND ->
                return MediatorResult.Success(endOfPaginationReached = true)

            LoadType.APPEND -> {
                val activityFeedPage = getRemoteKeysForLastItem(state)
                // There are three possible outcomes of getRemoteKeysForLastItem:
                // activityFeedPage is null: We are just starting pagination, allow to keep going
                // lastPage is true: This is a "real" end of pagination
                // otherwise: set page to nextPage, allow to keep going
                when {
                    activityFeedPage == null ->
                        return MediatorResult.Success(endOfPaginationReached = false)
                    activityFeedPage.lastPage ->
                        return MediatorResult.Success(endOfPaginationReached = true)
                    else -> activityFeedPage.nextPage
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
                    ifRight = { activityFeedPage ->
                        // Save
                        database.withTransaction {
                            if (loadType == LoadType.REFRESH) {
                                activityDao.clearActivityFeedAndActivities()
                            }
                            activityDao.insertActivitiesWithDetails(
                                activityFeedPage.activities.map { it.toLocal() }
                            )
                            activityDao.insertActivityFeedPageWithDetails(
                                activityFeedPage.toLocal(page)
                            )
                        }

                        MediatorResult.Success(endOfPaginationReached = activityFeedPage.lastPage)
                    }
                )
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, LocalActivityWithDetails>
    ): LocalActivityFeedPage? = state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.activity?.id?.let { activityId ->
            database.withTransaction {
                activityDao.getActivityFeedPageByActivityId(activityId).firstOrNull()
            }
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, LocalActivityWithDetails>
    ): LocalActivityFeedPage? = state.lastItemOrNull()?.let {
        database.withTransaction {
            activityDao.getActivityFeedPageByActivityId(it.activity.id).firstOrNull()
        }
    }

    // endregion Methods

}
