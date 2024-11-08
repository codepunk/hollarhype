package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(
    tableName = "message",
    foreignKeys = [
        ForeignKey(
            entity = LocalRun::class,
            parentColumns = ["id"],
            childColumns = ["run_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LocalMessage(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1L,
    @ColumnInfo(name = "run_id")
    val runId: Long? = null,
    @ColumnInfo(name = "sender_user_id")
    val senderUserId: Long? = null,
    @ColumnInfo(name = "recipient_user_id")
    val recipientUserId: Long? = null,
    @ColumnInfo(name = "voice_message")
    val voiceMessage: String = "",
    val favorite: Boolean = false,
    val transcript: String = "",
    val isPlaying: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Clock.System.now()
)
