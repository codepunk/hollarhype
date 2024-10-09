package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalGroup
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalActivityWithDetails(
    @Embedded
    val activity: LocalActivity = LocalActivity(),
    @Relation(
        parentColumn = "id",
        entityColumn = "group_id"
    )
    val group: LocalGroup? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "message_id"
    )
    val message: LocalMessageWithDetails? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "run_id"
    )
    val run: LocalRunWithDetails? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val user: LocalUser? = null
)
