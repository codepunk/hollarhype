package com.codepunk.hollarhype.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Message(
    val id: Long = -1L,
    val run: Run? = null,
    val sender: User? = null,
    val recipientUserId: Long? = null,
    val voiceMessage: String = "",
    val favorite: Boolean = false,
    val sponsor: Sponsor? = null,
    val transcript: String = "",
    val isPlaying: Boolean = false,
    val createdAt: Instant = Clock.System.now(),
)
