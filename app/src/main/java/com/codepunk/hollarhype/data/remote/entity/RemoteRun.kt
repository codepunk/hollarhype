package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRun(
    val id: Long = -1L,
    @SerialName("status_message")
    val statusMessage: String = "",
    val status: Status = Status.INACTIVE,
    @SerialName("start_time")
    @Serializable(with = InstantIso8601Serializer::class)
    val startTime: Instant? = null,
    @SerialName("end_time")
    @Serializable(with = InstantIso8601Serializer::class)
    val endTime: Instant? = null,
    val distance: Float = 0f,
    @SerialName("athlete")
    val user: RemoteUser = RemoteUser(),
    val messages: List<RemoteMessage> = emptyList(),
    @SerialName("enable_gps")
    val enableGps: Boolean = false,
    @SerialName("total_message_count")
    val totalMessageCount: Int = 0,
    @SerialName("created_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val createdAt: Instant = Clock.System.now(),
    @SerialName("updated_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val updatedAt: Instant = Clock.System.now()
) {

    // region Nested & inner classes

    enum class Status {

        @SerialName("inactive")
        INACTIVE,

        @SerialName("active")
        ACTIVE

    }

    // endregion Nested & inner classes

}
