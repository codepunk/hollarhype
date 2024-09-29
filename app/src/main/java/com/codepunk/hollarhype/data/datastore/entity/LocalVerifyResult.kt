package com.codepunk.hollarhype.data.datastore.entity

import kotlinx.serialization.Serializable

@Serializable
data class LocalVerifyResult(
    val userId: Long = -1L,
    val authToken: String = ""
)
