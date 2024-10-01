package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSocialProfile(
    @SerialName("athlete")
    val user: RemoteUser = RemoteUser(),
    @SerialName("sent_messages")
    val sentMessage: Int = 0,
    @SerialName("received_messages")
    val receivedMessages: Int = 0,
    @SerialName("favorite_audios")
    val favoriteAudios: List<RemoteMessage> = emptyList(),
    @SerialName("favorite_runs")
    val favoriteRuns: List<RemoteRun> = emptyList(),
    @SerialName("affiliations")
    val groups: List<RemoteGroup> = emptyList(),
    val campaigns: List<RemoteCampaign> = emptyList(),
    val cards: List<RemoteCard> = emptyList()
)
