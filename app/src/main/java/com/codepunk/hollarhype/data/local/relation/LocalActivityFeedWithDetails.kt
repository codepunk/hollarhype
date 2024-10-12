package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeed
import com.codepunk.hollarhype.data.local.entity.LocalRun

data class LocalActivityFeedWithDetails(
    @Embedded
    val activityFeed: LocalActivityFeed = LocalActivityFeed(),
    @Relation(
        entity = LocalActivity::class,
        parentColumn = "page",
        entityColumn = "id",
        associateBy = Junction(
            LocalActivityFeedActivityCrossRef::class
        )
    )
    val activities: List<LocalActivityWithDetails> = emptyList(),
    @Relation(
        entity = LocalRun::class,
        parentColumn = "id",
        entityColumn = "active_run_id"
    )
    val activeRun: LocalRunWithDetails? = null
)
