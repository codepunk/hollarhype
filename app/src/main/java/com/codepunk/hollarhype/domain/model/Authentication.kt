package com.codepunk.hollarhype.domain.model

data class Authentication(
    val user: User = User(),
    val authToken: String? = null
)
