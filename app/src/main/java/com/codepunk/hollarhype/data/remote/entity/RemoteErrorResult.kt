package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class RemoteErrorResult(
    val errors: List<String> = emptyList()
)
