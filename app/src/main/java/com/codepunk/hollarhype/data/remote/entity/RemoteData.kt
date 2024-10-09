package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class RemoteData(
    val group: RemoteGroup? = null,
    val user: RemoteUser? = null,
    val message: RemoteMessage? = null,
    val run: RemoteRun? = null
)
