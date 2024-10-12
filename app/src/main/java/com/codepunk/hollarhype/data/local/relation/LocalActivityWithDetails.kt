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
        parentColumn = "data_group_id",
        entityColumn = "id"
    )
    val group: LocalGroup? = null,
    @Relation(
        parentColumn = "data_message_id",
        entityColumn = "id"
    )
    val message: LocalMessage? = null, // TODO NEXT Make this LocalMessageWithDetails
    @Relation(
        parentColumn = "data_run_id",
        entityColumn = "id"
    )
    val run: LocalRun? = null, // TODO NEXT Make this LocalRunWithDetails
    @Relation(
        parentColumn = "data_user_id",
        entityColumn = "id"
    )
    val user: LocalUser? = null,
)
