package com.codepunk.hollarhype.domain.model

data class ActivityFeed(
    val activities: List<Activity> = emptyList(),
    val activeRun: Run?,
    val delta: Int = 0,
    val nextPage: Int,
    val lastPage: Boolean
)
