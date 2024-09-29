package com.codepunk.hollarhype.domain.model

sealed interface Authentication {
    val authenticated: Boolean

    data object Unauthenticated: Authentication {
        override val authenticated: Boolean = false
    }

    data class Authenticated(
        val user: User = User(),
        val authToken: String = ""
    ) : Authentication {
        override val authenticated: Boolean = true
    }

}
