package com.codepunk.hollarhype.util

import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.remote.entity.RemoteErrorResult
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.util.http.HttpStatusException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

fun CallError.toErrorResult(): ErrorResult =
    when (this) {
        is HttpError -> HttpStatusException.of(code, message).let { cause ->
            try {
                Json.decodeFromString<RemoteErrorResult>(body).toDomain(cause)
            } catch (e: SerializationException) {
                ErrorResult(cause = cause)
            }
        }
        is IOError -> ErrorResult(cause = cause)
        is UnexpectedCallError -> ErrorResult(cause = cause)
    }