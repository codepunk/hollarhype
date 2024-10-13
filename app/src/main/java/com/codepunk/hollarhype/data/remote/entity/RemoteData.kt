package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteData(
    @SerialName("affiliation")
    val group: RemoteGroup? = null,
    @SerialName("athlete")
    val user: RemoteUser? = null,
    val message: RemoteMessage? = null,
    val run: RemoteRun? = null
)
