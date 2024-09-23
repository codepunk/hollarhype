package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class RemoteAuthResult(
    val success: Boolean = false,
    val errors: List<String> = listOf()
)
