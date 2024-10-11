package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(
    tableName = "run",
    foreignKeys = [
        ForeignKey(
            entity = LocalUser::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class LocalRun(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1L,
    @ColumnInfo(name = "user_id")
    val userId: Long? = null,
    @ColumnInfo(name = "status_message")
    val statusMessage: String = "",
    val status: Status = Status.INACTIVE,
    @ColumnInfo(name = "start_time")
    val startTime: Instant? = null,
    @ColumnInfo(name = "end_time")
    val endTime: Instant? = null,
    val distance: Float = 0f,
    @ColumnInfo(name = "enable_gps")
    val enableGps: Boolean = false,
    @ColumnInfo(name = "total_message_count")
    val totalMessageCount: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Clock.System.now(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant = Clock.System.now()
) {

    // region Nested & inner classes

    enum class Status {
        INACTIVE,
        ACTIVE
    }

    // endregion Nested & inner classes

}

