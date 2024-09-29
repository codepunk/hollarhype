package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAuthentication(
    @SerialName("athlete")
    val user: RemoteUser = RemoteUser(),
    @SerialName("auth_token")
    val authToken: String = ""
)
