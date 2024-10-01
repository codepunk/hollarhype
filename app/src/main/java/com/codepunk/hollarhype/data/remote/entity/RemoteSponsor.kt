package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSponsor(
    @SerialName("id")
    val runId: Long = 0L,
    val name: String = "",
    @SerialName("text_copy")
    val textCopy: String = "",
    val url: String = "",
    @SerialName("sponsor_image")
    val sponsorImage: String? = null,
    val clicks: Int = 0,
    val active: Boolean = false,
    @SerialName("created_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val createdAt: Instant? = null,
    @SerialName("updated_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val updatedAt: Instant? = null
)
