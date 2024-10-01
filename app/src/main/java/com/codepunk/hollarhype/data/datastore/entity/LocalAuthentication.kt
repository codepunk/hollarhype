package com.codepunk.hollarhype.data.datastore.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface LocalAuthentication {
    val authenticated: Boolean
    val userId: Long?

    @Serializable
    @SerialName("unauthenticated")
    data object LocalUnauthenticated : LocalAuthentication {
        override val authenticated: Boolean = false
        override val userId: Long? = null
    }

    @Serializable
    @SerialName("authenticated")
    data class LocalAuthenticated(
        override val userId: Long = -1L,
        val authToken: String = ""
    ) : LocalAuthentication {
        override val authenticated: Boolean = true
    }

}

