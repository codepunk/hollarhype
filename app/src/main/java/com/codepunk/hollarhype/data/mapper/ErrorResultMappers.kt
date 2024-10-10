package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.remote.entity.RemoteError
import com.codepunk.hollarhype.domain.repository.RepositoryException
import com.codepunk.hollarhype.util.http.HttpStatusException

fun RemoteError.toRepositoryException(
    code: Int,
    message: String?
): RepositoryException = RepositoryException(
    errors = errors,
    cause = HttpStatusException.of(
        code = code,
        message = message
    )
)
