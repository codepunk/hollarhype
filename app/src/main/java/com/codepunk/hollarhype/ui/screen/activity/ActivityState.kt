package com.codepunk.hollarhype.ui.screen.activity

import androidx.paging.PagingData
import com.codepunk.hollarhype.domain.model.Activity

data class ActivityState(
    val activityFeed: PagingData<Activity> = PagingData.empty()
)
