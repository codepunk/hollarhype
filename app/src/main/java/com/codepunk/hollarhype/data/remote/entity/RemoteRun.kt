package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRun(
    val id: Long = 0L,
    @SerialName("status_message")
    val statusMessage: String = "",
    val status: Status = Status.INACTIVE,
    @SerialName("start_time")
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val startTime: LocalDateTime? = null,
    @SerialName("end_time")
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val endTime: LocalDateTime? = null,
    val distance: Float = 0f,
    @SerialName("athlete")
    val user: RemoteUser = RemoteUser(),
    val messages: List<RemoteMessage> = emptyList(),
    @SerialName("athlete_id")
    val userId: Long = 0L,
    @SerialName("enable_gps")
    val enableGps: Boolean = false,
    @SerialName("total_message_count")
    val totalMessageCount: Int = 0,
    @SerialName("created_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val createdAt: Instant? = null,
    @SerialName("updated_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val updatedAt: Instant? = null
) {

    // region Nested & inner classes

    @Serializable
    enum class Status {

        @SerialName("inactive")
        INACTIVE,

        @SerialName("active")
        ACTIVE

    }

    // endregion Nested & inner classes

}
