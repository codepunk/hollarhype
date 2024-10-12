package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.remote.entity.RemoteMessage
import com.codepunk.hollarhype.domain.model.Message

fun RemoteMessage.toLocal(): LocalMessage = LocalMessage(
    id = id,
    senderUserId = senderUserId,
    recipientUserId = recipientUserId,
    voiceMessage = voiceMessage,
    favorite = favorite,
    transcript = transcript,
    isPlaying = isPlaying,
    createdAt = createdAt
)

/* TODO TEMP
 *  Replace with LocalMessageWithDetails.toDomain()
 */
fun LocalMessage.toDomain(): Message = Message(
    id = id,
    sender = null,
    recipientUserId = recipientUserId,
    voiceMessage = voiceMessage,
    favorite = favorite,
    sponsor = null,
    transcript = transcript,
    isPlaying = isPlaying,
    createdAt = createdAt
)
