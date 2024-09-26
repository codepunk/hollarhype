package com.codepunk.hollarhype.domain.model

data class Authentication(
    val user: User,
    val authToken: String
)
