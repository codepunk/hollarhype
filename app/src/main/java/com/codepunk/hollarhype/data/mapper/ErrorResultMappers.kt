package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.remote.entity.RemoteError
import com.codepunk.hollarhype.domain.repository.DataError
import com.codepunk.hollarhype.util.http.HttpStatusException

fun RemoteError.toDataError(
    code: Int,
    message: String?
): DataError = DataError(
    errors = errors,
    cause = HttpStatusException.of(
        code = code,
        message = message
    )
)
