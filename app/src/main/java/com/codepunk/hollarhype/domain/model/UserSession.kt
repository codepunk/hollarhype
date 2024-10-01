package com.codepunk.hollarhype.domain.model

sealed interface UserSession {
    val userId: Long?
    val authToken: String?
}

data object Unauthenticated: UserSession {
    override val userId: Long? = null
    override val authToken: String? = null
}

data class Authenticated(
    override val userId: Long = 0,
    override val authToken: String = ""
) : UserSession
