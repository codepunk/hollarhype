package com.codepunk.hollarhype.util

import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthResult
import com.codepunk.hollarhype.util.http.HttpStatus
import com.codepunk.hollarhype.util.http.HttpStatusException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

fun CallError.toThrowable(): Throwable =
    when (this) {
        is HttpError -> {
            val bodyMessage = try {
                Json.decodeFromString<RemoteAuthResult>(body).errors.joinToString("\n") { it }
            } catch (e: SerializationException) {
                ""
            }
            HttpStatusException(
                httpStatus = HttpStatus.lookup(
                    code = code,
                    reasonPhrase = bodyMessage.ifEmpty { message }
                ),
                message = bodyMessage
            )
        }
        is IOError -> cause
        is UnexpectedCallError -> cause
    }