package com.codepunk.hollarhype.data.datastore.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface LocalUserSession {
    val userId: Long?
    val authToken: String?
}

@Serializable
@SerialName("unauthenticated")
data object LocalUnauthenticated : LocalUserSession {
    override val userId: Long? = null
    override val authToken: String? = null
}

@Serializable
@SerialName("authenticated")
data class LocalAuthenticated(
    override val userId: Long = -1L,
    override val authToken: String = ""
) : LocalUserSession
