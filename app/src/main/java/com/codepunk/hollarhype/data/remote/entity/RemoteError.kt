package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class RemoteError(
    val errors: List<String> = emptyList()
)
