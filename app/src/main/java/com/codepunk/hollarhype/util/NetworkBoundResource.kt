package com.codepunk.hollarhype.util

import arrow.core.Either
import arrow.core.Ior
import arrow.core.rightIor
import com.codepunk.hollarhype.domain.repository.RepositoryError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <Domain, Remote, Error> networkBoundResource(
    crossinline query: () -> Flow<Domain>,
    crossinline fetch: suspend () -> Either<Error, Remote>,
    crossinline onFetchCaught: (Throwable) -> Error,
    crossinline onFetchFailed: (Error) -> Unit = {},
    crossinline saveFetchResult: suspend (Remote) -> Unit = {},
    crossinline shouldFetch: (Domain?) -> Boolean = { true }
): Flow<Ior<Error, Domain?>> = flow {
    val data = query().firstOrNull()
    val flow = if (shouldFetch(data)) {
        emit(data.rightIor())
        try {
            fetch().fold(
                ifLeft = { onFetchFailed(it) },
                ifRight = { saveFetchResult(it) }
            )
            query().map { it.rightIor() }
        } catch (throwable: Throwable) {
            val error = onFetchCaught(throwable)
            query().map { Ior.Both(error, data) }
        }
    } else {
        query().map { it.rightIor() }
    }
    emitAll(flow)
}

inline fun <Domain, Remote> networkBoundResource(
    crossinline query: () -> Flow<Domain>,
    crossinline fetch: suspend () -> Either<RepositoryError, Remote>,
    crossinline onFetchFailed: (RepositoryError) -> Unit = {},
    crossinline saveFetchResult: suspend (Remote) -> Unit = {},
    crossinline shouldFetch: (Domain?) -> Boolean = { true }
): Flow<Ior<RepositoryError, Domain?>> = networkBoundResource(
    query = query,
    fetch = fetch,
    saveFetchResult = saveFetchResult,
    onFetchCaught = { throwable -> RepositoryError(cause = throwable) },
    onFetchFailed = onFetchFailed,
    shouldFetch = shouldFetch
)
