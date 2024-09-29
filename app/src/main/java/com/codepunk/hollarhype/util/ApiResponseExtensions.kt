package com.codepunk.hollarhype.util

import com.hadiyarajesh.flower_core.ApiEmptyResponse
import com.hadiyarajesh.flower_core.ApiErrorResponse
import com.hadiyarajesh.flower_core.ApiResponse
import com.hadiyarajesh.flower_core.ApiSuccessResponse

fun <T, R> ApiResponse<T>.map(
    transform: (T) -> R
): ApiResponse<R> = when (this) {
    is ApiEmptyResponse -> ApiEmptyResponse()
    is ApiErrorResponse -> ApiErrorResponse(
        errorMessage = errorMessage,
        httpStatusCode = httpStatusCode
    )
    is ApiSuccessResponse -> ApiSuccessResponse(
        body = body?.run { transform(this) },
        headers = headers
    )
}
