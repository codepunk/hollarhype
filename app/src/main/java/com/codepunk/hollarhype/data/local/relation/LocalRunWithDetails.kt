package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalRunWithDetails(
    @Embedded
    val run: LocalRun,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val user: LocalUser,
    @Relation(
        parentColumn = "id",
        entityColumn = "message_id"
    )
    val messages: List<LocalMessageWithDetails>
)
