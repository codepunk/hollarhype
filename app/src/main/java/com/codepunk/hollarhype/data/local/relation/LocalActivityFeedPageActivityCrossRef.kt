package com.codepunk.hollarhype.data.local.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeedPage

@Entity(
    tableName = "activity_feed_page_activity_cross_ref",
    primaryKeys = ["activity_feed_page", "activity_id"],
    indices = [
        Index("activity_feed_page"),
        Index("activity_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = LocalActivityFeedPage::class,
            parentColumns = ["page"],
            childColumns = ["activity_feed_page"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LocalActivity::class,
            parentColumns = ["id"],
            childColumns = ["activity_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LocalActivityFeedPageActivityCrossRef(
    @ColumnInfo(name = "activity_feed_page")
    val activityFeedPage: Int = 0,
    @ColumnInfo(name = "activity_id")
    val activityId: Long = -1L
)
