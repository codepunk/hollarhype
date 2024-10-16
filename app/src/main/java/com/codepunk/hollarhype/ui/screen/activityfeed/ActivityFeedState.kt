package com.codepunk.hollarhype.ui.screen.activityfeed

import androidx.paging.PagingData
import com.codepunk.hollarhype.domain.model.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ActivityFeedState(
    val activityFeedFlow: Flow<PagingData<Activity>> = flowOf(PagingData.empty()),
    val isActivityFeedFresh: Boolean = false
)
