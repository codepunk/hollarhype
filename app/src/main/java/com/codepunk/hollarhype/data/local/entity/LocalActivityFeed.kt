package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_feed")
data class LocalActivityFeed(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "activity_id")
    val activityId: Long = -1L,
    val delta: Long = 0L,
    @ColumnInfo(name = "next_page")
    val nextPage: Int = 0,
    @ColumnInfo(name = "last_page")
    val lastPage: Boolean = true
)
