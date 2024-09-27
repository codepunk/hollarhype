package com.codepunk.hollarhype.domain.model

data class VerifyResult(
    val user: User = User(),
    val authToken: String = ""
)
