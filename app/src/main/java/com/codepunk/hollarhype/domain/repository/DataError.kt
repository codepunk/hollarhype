package com.codepunk.hollarhype.domain.repository

data class DataError(
    val errors: List<String> = emptyList(),
    val cause: Throwable? = null
)
