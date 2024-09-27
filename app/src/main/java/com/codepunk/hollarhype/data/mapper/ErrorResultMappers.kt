package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.remote.entity.RemoteErrorResult
import com.codepunk.hollarhype.domain.repository.RepositoryError

fun RemoteErrorResult.toDomain(cause: Throwable? = null): RepositoryError = RepositoryError(
    errors = errors,
    cause = cause
)