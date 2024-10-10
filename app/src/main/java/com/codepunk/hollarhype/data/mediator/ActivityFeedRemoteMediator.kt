package com.codepunk.hollarhype.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import arrow.retrofit.adapter.either.ResponseE
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.remote.entity.RemoteActivityFeed
import com.codepunk.hollarhype.data.remote.entity.RemoteError
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import okio.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ActivityFeedRemoteMediator @Inject constructor(
    val webservice: HollarhypeWebservice,
    val database: HollarhypeDatabase
) : RemoteMediator<Int, ResponseE<RemoteError, RemoteActivityFeed>>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResponseE<RemoteError, RemoteActivityFeed>>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND ->
                    state.lastItemOrNull()?.body?.getOrNull()?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            val activityFeed = webservice.activityFeed(
                page = page
            )



            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.activityDao().clearActivities()

                }
            }

            MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}
