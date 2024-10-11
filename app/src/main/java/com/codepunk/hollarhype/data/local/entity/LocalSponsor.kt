package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(
    tableName = "sponsor"
)
data class LocalSponsor(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "run_id")
    val runId: Long = -1L,
    val name: String = "",
    @ColumnInfo(name = "text_copy")
    val textCopy: String = "",
    val url: String = "",
    @ColumnInfo(name = "sponsor_image")
    val sponsorImage: String? = null,
    val clicks: Int = 0,
    val active: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Clock.System.now(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant = Clock.System.now()
)
