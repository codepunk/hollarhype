package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalRunWithDetails(
    @Embedded
    val run: LocalRun = LocalRun(),
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val user: LocalUser = LocalUser(),
    @Relation(
        parentColumn = "id",
        entityColumn = "run_id"
    )
    val messages: List<LocalMessage> = emptyList()
)
