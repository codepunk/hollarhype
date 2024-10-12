package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalActivityFeed
import com.codepunk.hollarhype.data.local.relation.LocalActivityFeedWithDetails
import com.codepunk.hollarhype.data.remote.entity.RemoteActivityFeed

fun RemoteActivityFeed.toLocal(
    page: Int
): LocalActivityFeedWithDetails = LocalActivityFeedWithDetails(
    activityFeed = LocalActivityFeed(
        page = page,
        activeRunId = activeRun?.id,
        nextPage = nextPage,
        lastPage = lastPage
    ),
    activities = activities.map { it.toLocal() },
    activeRun = activeRun?.toLocal()
)
