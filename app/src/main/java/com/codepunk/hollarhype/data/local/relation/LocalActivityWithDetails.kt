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
    val dataGroup: LocalGroup? = null,
    @Relation(
        entity = LocalMessage::class,
        parentColumn = "data_message_id",
        entityColumn = "id"
    )
    val dataMessage: LocalMessageWithDetails? = null,
    @Relation(
        entity = LocalRun::class,
        parentColumn = "data_run_id",
        entityColumn = "id"
    )
    val dataRun: LocalRunWithDetails? = null,
    @Relation(
        parentColumn = "data_user_id",
        entityColumn = "id"
    )
    val dataUser: LocalUser? = null,
)
