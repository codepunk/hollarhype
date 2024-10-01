package com.codepunk.hollarhype.domain.model

sealed interface Authentication {
    val authenticated: Boolean

    data object Unauthenticated: Authentication {
        override val authenticated: Boolean = false
    }

    data class Authenticated(
        val userId: Long = 0,
        val authToken: String = ""
    ) : Authentication {
        override val authenticated: Boolean = true
    }

}
