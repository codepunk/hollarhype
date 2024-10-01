package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAuthenticateResult(
    @SerialName("athlete")
    val user: RemoteUser = RemoteUser()
)
