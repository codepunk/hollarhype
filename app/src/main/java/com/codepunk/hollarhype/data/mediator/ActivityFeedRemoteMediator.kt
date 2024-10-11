package com.codepunk.hollarhype.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeed
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import kotlinx.coroutines.flow.firstOrNull
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ActivityFeedRemoteMediator @Inject constructor(
    private val webservice: HollarhypeWebservice,
    private val database: HollarhypeDatabase
) : RemoteMediator<Int, LocalActivity>() {

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalActivity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                // TODO Simplify this?
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

        try {
            webservice.activityFeed(page = page).run {
                val x = "$body"
                body
            }
            TODO("Not yet implemented")
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: NotImplementedError) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getActivityFeedClosestToCurrentPosition(
        state: PagingState<Int, LocalActivity>
    ): LocalActivityFeed? = state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.id?.let { activityId ->
            database.withTransaction {
                database.activityFeedDao().getActivityFeedByActivityId(activityId).firstOrNull()
            }
        }
    }

    private suspend fun getActivityFeedForLastItem(
        state: PagingState<Int, LocalActivity>
    ): LocalActivityFeed? = state.lastItemOrNull()?.let {
        database.withTransaction {
            database.activityFeedDao().getActivityFeedByActivityId(
                it.id
            ).firstOrNull()
        }
    }
}
