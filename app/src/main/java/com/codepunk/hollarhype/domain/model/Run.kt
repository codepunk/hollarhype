package com.codepunk.hollarhype.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Run(
    val id: Long = -1L,
    val statusMessage: String = "",
    val status: Status = Status.INACTIVE,
    val startTime: Instant? = null,
    val endTime: Instant? = null,
    val distance: Float = 0f,
    val user: User? = null,
    val messages: List<Message> = emptyList(),
    val enableGps: Boolean = false,
    val totalMessageCount: Int = 0,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now()
) {

    // region Nested & inner classes

    enum class Status {
        INACTIVE,
        ACTIVE
    }

    // endregion Nested & inner classes

}
