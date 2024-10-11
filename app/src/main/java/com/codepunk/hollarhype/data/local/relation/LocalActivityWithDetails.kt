package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalGroup
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalActivityWithDetails(
    @Embedded
    val activity: LocalActivity = LocalActivity(),
    @Relation(
        parentColumn = "group_id",
        entityColumn = "id"
    )
    val group: LocalGroup? = null,
    @Relation(
        parentColumn = "message_id",
        entityColumn = "id"
    )
    val message: LocalMessage? = null,
    @Relation(
        parentColumn = "run_id",
        entityColumn = "id"
    )
    val run: LocalRun? = null,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val user: LocalUser? = null,
)
