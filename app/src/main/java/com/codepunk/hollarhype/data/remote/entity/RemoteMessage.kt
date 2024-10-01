package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMessage(
    val id: Long = 0L,
    @SerialName("sender_id")
    val senderId: Int,
    @SerialName("recipient_id")
    val recipientId: Int,
    @SerialName("voice_message")
    val voiceMessage: String,
    @SerialName("sender_details")
    val senderDetails: RemoteUser = RemoteUser(),
    val run: RemoteRun = RemoteRun(),
    val favorite: Boolean = false,
    val sponsor: RemoteSponsor = RemoteSponsor(),
    val transcript: String = "",
    val isPlaying: Boolean = false,
    @SerialName("created_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val createdAt: Instant? = null
)
