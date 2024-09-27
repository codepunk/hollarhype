package com.codepunk.hollarhype.domain.repository

data class RepositoryError(
    val errors: List<String> = emptyList(),
    val cause: Throwable? = null
)
