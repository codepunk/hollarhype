package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class RemoteLoginResult(
    val success: Boolean
)
