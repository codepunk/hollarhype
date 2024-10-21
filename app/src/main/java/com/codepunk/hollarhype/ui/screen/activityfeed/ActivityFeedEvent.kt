package com.codepunk.hollarhype.ui.screen.activityfeed

import com.codepunk.hollarhype.domain.model.Activity

sealed interface ActivityFeedEvent {

    // Navigation

    // Refresh data

    data object Load : ActivityFeedEvent

    // User interaction

    data class SelectActivity(val activity: Activity) : ActivityFeedEvent

    data object SelectNone : ActivityFeedEvent

    // Events/results

    data object ConsumeActivityFeed: ActivityFeedEvent


}