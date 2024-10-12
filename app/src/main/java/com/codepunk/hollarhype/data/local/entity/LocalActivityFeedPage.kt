package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_feed")
data class LocalActivityFeedPage(
    @PrimaryKey(autoGenerate = false)
    val page: Int = 0,
    @ColumnInfo(name = "active_run_id")
    val activeRunId: Long? = null,
    @ColumnInfo(name = "next_page")
    val nextPage: Int = 0,
    @ColumnInfo(name = "last_page")
    val lastPage: Boolean = true
)
