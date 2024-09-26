package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName

data class RemoteAuthentication(
    @SerialName("athlete")
    val user: RemoteUser,
    @SerialName("auth_token")
    val authToken: String
)
