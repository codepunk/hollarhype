package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRuns(
    val runs: List<RemoteRun> = emptyList(),
    @SerialName("active_run")
    val activeRun: RemoteRun? = null,
    val delta: Int = 0,
    @SerialName("next_page")
    val nextPage: Int = 0,
    @SerialName("last_page")
    val lastPage: Boolean = true
)
