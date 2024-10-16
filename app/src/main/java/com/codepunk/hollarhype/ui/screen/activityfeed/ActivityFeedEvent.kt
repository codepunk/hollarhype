package com.codepunk.hollarhype.ui.screen.activityfeed

sealed interface ActivityFeedEvent {

    // Navigation

    // Refresh data

    data object Load : ActivityFeedEvent

    // Events/results

    data object ConsumeActivityFeed: ActivityFeedEvent


}