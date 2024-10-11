package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteActivity(
    val id: Long = -1L,
    @SerialName("target_id")
    val targetId: Long? = null,
    val data: RemoteData = RemoteData(),
    @SerialName("activity_text")
    val activityText: String = "",
    @SerialName("activity_type")
    val activityType: Type = Type.FINISH_RUN,
    @SerialName("athlete_id")
    val userId: Long? = null,
    @SerialName("created_at")
    val createdAt: Instant = Clock.System.now()
) {

    // region Nested & inner classes

    enum class Type {
        @SerialName("create_group")
        CREATE_GROUP,

        @SerialName("finish_run")
        FINISH_RUN,

        @SerialName("join_affiliation")
        JOIN_GROUP,

        @SerialName("live")
        LIVE,

        @SerialName("voice_message")
        VOICE_MESSAGE
    }

    // endregion Nested & inner classes

}
