package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.entity.LocalSponsor
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalMessageWithDetails(
    @Embedded
    val message: LocalMessage = LocalMessage(),
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val sender: LocalUser? = null,
    @Relation(
        parentColumn = "run_id",
        entityColumn = "run_id"
    )
    val sponsor: LocalSponsor? = null
)
