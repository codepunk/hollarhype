package com.codepunk.hollarhype.domain.model

data class ErrorResult(
    val errors: List<String> = emptyList(),
    val cause: Throwable? = null
)
