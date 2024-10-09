package com.codepunk.hollarhype.domain.model

data class SocialProfile(
    val user: User = User(),
    val sentMessages: Int = 0,
    val receivedMessages: Int = 0,
    val favoriteAudios: List<Message> = emptyList(),
    val favoriteRuns: List<Run> = emptyList(),
    val groups: List<Group> = emptyList(),
    val campaigns: List<Campaign> = emptyList(),
    val cards: List<Card> = emptyList()
)
