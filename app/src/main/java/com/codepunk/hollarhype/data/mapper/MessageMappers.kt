package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.relation.LocalMessageWithDetails
import com.codepunk.hollarhype.data.remote.entity.RemoteMessage
import com.codepunk.hollarhype.domain.model.Message

fun RemoteMessage.toLocal(): LocalMessage = LocalMessage(
    id = id,
    runId = run?.id,
    senderUserId = senderUserId,
    recipientUserId = recipientUserId,
    sponsorId = sponsor?.id,
    voiceMessage = voiceMessage,
    favorite = favorite,
    transcript = transcript,
    isPlaying = isPlaying,
    createdAt = createdAt
)

fun RemoteMessage.toLocalWithDetails(): LocalMessageWithDetails = LocalMessageWithDetails(
    message = toLocal(),
    run = run?.toLocalWithDetails(),
    sender = sender?.toLocal()
)

fun LocalMessageWithDetails.toDomain(): Message = Message(
    id = message.id,
    run = run?.toDomain(),
    sender = sender?.toDomain(),
    recipientUserId = message.recipientUserId,
    voiceMessage = message.voiceMessage,
    favorite = message.favorite,
    sponsor = sponsor?.toDomain(),
    transcript = message.transcript,
    isPlaying = message.isPlaying,
    createdAt = message.createdAt
)
