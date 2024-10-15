package com.codepunk.hollarhype.ui.screen.activity

sealed interface ActivityEvent {

    // Navigation

    // Refresh data

    data object Load : ActivityEvent

    // Events/results

    data object ConsumeActivityFeed: ActivityEvent


}