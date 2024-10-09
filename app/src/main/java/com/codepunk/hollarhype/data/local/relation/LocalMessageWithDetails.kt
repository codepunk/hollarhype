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
        parentColumn = "id",
        entityColumn = "run_id"
    )
    val run: LocalRunWithDetails? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "sender_user_id"
    )
    val sender: LocalUser? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "sponsor_id"
    )
    val sponsor: LocalSponsor? = null

)
