package com.codepunk.hollarhype.util

import android.content.Context
import android.net.ConnectivityManager
import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthResult
import com.codepunk.hollarhype.util.http.HttpStatus
import com.codepunk.hollarhype.util.http.HttpStatusException
import com.codepunk.hollarhype.util.http.NoConnectivityException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

fun CallError.toThrowable(
    context: Context? = null,
    connectivityManager: ConnectivityManager? = null
): Throwable =
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
        is IOError -> {
            // TODO Is there a more elegant way to do this, i.e.
            // have a NoConnectivityException sent to EitherCallAdapterFactory ?
            when {
                connectivityManager == null -> cause
                !connectivityManager.isConnected() -> NoConnectivityException(
                    message = context?.getString(R.string.no_internet_try_again)
                        ?: "No Internet connection"
                )
                else -> cause
            }
        }
        is UnexpectedCallError -> cause
    }