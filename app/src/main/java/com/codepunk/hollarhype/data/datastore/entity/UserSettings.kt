package com.codepunk.hollarhype.data.datastore.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val authentication: LocalVerifyResult = LocalVerifyResult()
)
