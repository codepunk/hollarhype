package com.codepunk.hollarhype.ui.screen.activity

import com.codepunk.hollarhype.domain.model.Activity

data class ActivityState(
    val activityId: Long = -1L, // TODO TEMP
    val activity: Activity? = null
)
