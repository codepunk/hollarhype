package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(tableName = "activity")
data class LocalActivity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1L,
    @ColumnInfo(name = "target_id")
    val targetId: Long? = null,
    @ColumnInfo(name = "data_group_id")
    val targetGroupId: Long? = null,
    @ColumnInfo(name = "data_message_id")
    val targetMessageId: Long? = null,
    @ColumnInfo(name = "data_run_id")
    val targetRunId: Long? = null,
    @ColumnInfo(name = "data_user_id")
    val targetUserId: Long? = null,
    @ColumnInfo(name = "activity_text")
    val activityText: String = "",
    @ColumnInfo(name = "activity_type")
    val activityType: Type = Type.FINISH_RUN,
    @ColumnInfo(name = "user_id")
    val userId: Long? = null,
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
