package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.relation.LocalMessageWithDetails
import com.codepunk.hollarhype.data.remote.entity.RemoteMessage
import com.codepunk.hollarhype.domain.model.Message

fun RemoteMessage.toLocal(): LocalMessageWithDetails = LocalMessageWithDetails(
    message = LocalMessage(
        id = id,
        senderUserId = senderUserId,
        recipientUserId = recipientUserId,
        voiceMessage = voiceMessage,
        favorite = favorite,
        transcript = transcript,
        isPlaying = isPlaying,
        createdAt = createdAt
    ),
    sender = sender?.toLocal(),
    sponsor = sponsor?.toLocal()

)

fun LocalMessageWithDetails.toDomain(): Message = Message(
    id = message.id,
    sender = sender?.toDomain(),
    recipientUserId = message.recipientUserId,
    voiceMessage = message.voiceMessage,
    favorite = message.favorite,
    sponsor = sponsor?.toDomain(),
    transcript = message.transcript,
    isPlaying = message.isPlaying,
    createdAt = message.createdAt
)
