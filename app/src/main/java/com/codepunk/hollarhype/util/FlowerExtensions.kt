package com.codepunk.hollarhype.util

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.util.http.HttpStatusException
import com.hadiyarajesh.flower_core.ApiResponse
import com.hadiyarajesh.flower_core.Resource
import com.hadiyarajesh.flower_core.Resource.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import com.hadiyarajesh.flower_core.networkResource
import kotlinx.coroutines.flow.flowOf

inline fun <Remote, Domain> networkResource(
    crossinline doStatusCheck: () -> Unit = {},
    crossinline makeNetworkRequest: suspend () -> ApiResponse<Remote>,
    crossinline onNetworkRequestFailed: (e: Exception) -> Unit = {},
    crossinline transform: (Remote) -> Domain
): Flow<Either<RepositoryError, Domain>> = try {
    doStatusCheck()
    networkResource(
        makeNetworkRequest,
        onNetworkRequestFailed = { errorMessage, httpStatusCode ->
            onNetworkRequestFailed(
                HttpStatusException.of(
                    code = httpStatusCode,
                    message = errorMessage
                )
            )
        }
    ).mapToNetworkFlow { transform(it) }
} catch (e: Exception) {
    flowOf(RepositoryError(cause = e).left())
}

fun <Remote, Domain> Flow<Resource<Remote>>.mapToNetworkFlow(
    transform: (Remote) -> Domain
): Flow<Either<RepositoryError, Domain>> =
    filterNot {
        it.status is Status.Loading
    }.map { resource ->
        when (val status = resource.status) {
            is Status.EmptySuccess -> status.toRepositoryError().left()
            is Status.Error -> status.toRepositoryError().left()
            is Status.Loading -> throw IllegalStateException("Unreachable code")
            is Status.Success -> transform(status.data).right()
        }
    }