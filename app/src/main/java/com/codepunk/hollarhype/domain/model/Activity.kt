package com.codepunk.hollarhype.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Activity(
    val id: Long = -1L,
    val activityText: String = "",
    val activityType: Type = Type.FINISH_RUN,
    val targetId: Long? = null,
    val data: Data = Data(),
    val createdAt: Instant = Clock.System.now()
) {

    // region Nested & inner classes

    enum class Type {
        CREATE_GROUP,
        FINISH_RUN,
        JOIN_GROUP,
        LIVE,
        VOICE_MESSAGE
    }

    // endregion Nested & inner classes

}
