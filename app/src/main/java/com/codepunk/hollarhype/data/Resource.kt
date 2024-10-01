package com.codepunk.hollarhype.data

import arrow.core.Either
import arrow.core.Ior
import arrow.core.left
import arrow.core.leftIor
import arrow.core.right
import arrow.core.rightIor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

inline fun <Remote, Domain, Error> networkResource(
    crossinline fetch: suspend () -> Either<Error, Remote>,
    crossinline onFetchCaught: (Throwable) -> Error,
    crossinline onFetchFailed: (Error) -> Unit = {},
    crossinline transform: (Remote) -> Domain
): Flow<Either<Error, Domain>> = flow {
    emit(
        try {
            fetch()
        } catch (cause: Throwable) {
            onFetchCaught(cause).left()
        }.onLeft {
            onFetchFailed(it)
        }.map {
            transform(it)
        }
    )
}

inline fun <Remote, Domain, Error> cachedResource(
    crossinline query: suspend () -> Flow<Domain?>,
    crossinline onNoData: () -> Error,
    crossinline fetch: suspend () -> Either<Error, Remote>,
    crossinline onFetchCaught: (Throwable) -> Error,
    crossinline onFetchFailed: (Error) -> Unit = {},
    crossinline shouldFetch: (Domain?) -> Boolean = { true },
    crossinline saveFetchResult: suspend (Remote) -> Unit = {}
): Flow<Ior<Error, Domain>> = flow {
    emit(
        query().first().let { cached ->
            if (shouldFetch(cached)) {
                try {
                    fetch().map {
                        saveFetchResult(it)
                        query().first()
                    }
                } catch (cause: Throwable) {
                    onFetchCaught(cause).left()
                }.onLeft { onFetchFailed(it) }
            } else {
                cached.right()
            }.fold(
                ifLeft = { it.leftIor() },
                ifRight = {
                    when (it) {
                        null -> onNoData().leftIor()
                        else -> it.rightIor()
                    }
                }
            )
        }
    )
}