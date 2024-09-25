package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.remote.entity.RemoteErrorResult
import com.codepunk.hollarhype.domain.model.ErrorResult

fun RemoteErrorResult.toDomain(cause: Throwable? = null): ErrorResult = ErrorResult(
    errors = errors,
    cause = cause
)