package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMessage(
    val id: Long = -1L,
    @SerialName("sender_id")
    val senderUserId: Long? = null,
    @SerialName("sender_details")
    val sender: RemoteUser? = null,
    @SerialName("recipient_id")
    val recipientUserId: Long? = null,
    @SerialName("voice_message")
    val voiceMessage: String,
    val favorite: Boolean = false,
    val sponsor: RemoteSponsor? = null,
    val transcript: String = "",
    val isPlaying: Boolean = false,
    @SerialName("created_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val createdAt: Instant = Clock.System.now()
)
