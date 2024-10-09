package com.codepunk.hollarhype.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.Activity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ActivityFeedRemoteMediator @Inject constructor(
    val webservice: HollarhypeWebservice,
    val database: HollarhypeDatabase
) : RemoteMediator<Int, Activity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Activity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}
