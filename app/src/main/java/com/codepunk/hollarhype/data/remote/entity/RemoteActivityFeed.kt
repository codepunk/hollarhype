package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteActivityFeed(
    val activities: List<RemoteActivity> = emptyList(),
    @SerialName("active_run")
    val activeRun: RemoteRun?,
    val delta: Int = 0,
    @SerialName("next_page")
    val nextPage: Int,
    @SerialName("last_page")
    val lastPage: Boolean
)
