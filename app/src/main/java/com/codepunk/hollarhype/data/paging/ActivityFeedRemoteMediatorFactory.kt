package com.codepunk.hollarhype.data.paging

import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityFeedRemoteMediatorFactory @Inject constructor(
    private val webservice: HollarhypeWebservice,
    private val database: HollarhypeDatabase
) {

    // region Methods

    fun create(
        deviceDateTime: LocalDateTime
    ): ActivityFeedRemoteMediator = ActivityFeedRemoteMediator(
        deviceDateTime = deviceDateTime,
        webservice = webservice,
        database = database,
    )

    // endregion Methods

}