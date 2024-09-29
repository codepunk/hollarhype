package com.codepunk.hollarhype.util

import com.codepunk.hollarhype.data.remote.entity.RemoteErrorResult
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.util.http.HttpStatusException
import com.hadiyarajesh.flower_core.Resource.Status
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

fun <T> Status.Error<T>.toRepositoryError(): RepositoryError = RepositoryError(
    errors = try {
        Json.decodeFromString<RemoteErrorResult>(errorMessage).errors
    } catch (e: SerializationException) {
        listOf(e.message.orEmpty())
    },
    cause = HttpStatusException.of(code = httpStatusCode)
)

fun Status.EmptySuccess.toRepositoryError(): RepositoryError = this.run {
    RepositoryError(
        cause = IllegalStateException("Remote call returned empty body")
    )
}
