package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(
    tableName = "activity",
    foreignKeys = [
        ForeignKey(
            entity = LocalGroup::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = LocalMessage::class,
            parentColumns = ["id"],
            childColumns = ["message_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = LocalRun::class,
            parentColumns = ["id"],
            childColumns = ["run_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = LocalUser::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = LocalUser::class,
            parentColumns = ["id"],
            childColumns = ["target_user_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.SET_NULL
        ),
    ]
)
data class LocalActivity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1L,
    @ColumnInfo(name = "group_id")
    val groupId: Long? = null,
    @ColumnInfo(name = "message_id")
    val messageId: Long? = null,
    @ColumnInfo(name = "run_id")
    val runId: Long? = null,
    @ColumnInfo(name = "user_id")
    val userId: Long? = null,
    @ColumnInfo(name = "target_user_id")
    val targetUserId: Long? = null,
    @ColumnInfo(name = "activity_text")
    val activityText: String = "",
    @ColumnInfo(name = "activity_type")
    val activityType: Type = Type.FINISH_RUN,
    @ColumnInfo(name = "created_at")
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
