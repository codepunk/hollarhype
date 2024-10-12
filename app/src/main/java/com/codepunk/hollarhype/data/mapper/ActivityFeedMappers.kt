package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalActivityFeedPage
import com.codepunk.hollarhype.data.local.relation.LocalActivityFeedPageWithDetails
import com.codepunk.hollarhype.data.remote.entity.RemoteActivityFeedPage

fun RemoteActivityFeedPage.toLocal(
    page: Int
): LocalActivityFeedPageWithDetails = LocalActivityFeedPageWithDetails(
    activityFeed = LocalActivityFeedPage(
        page = page,
        activeRunId = activeRun?.id,
        nextPage = nextPage,
        lastPage = lastPage
    ),
    activities = activities.map { it.toLocal() },
    activeRun = activeRun?.toLocal()
)
