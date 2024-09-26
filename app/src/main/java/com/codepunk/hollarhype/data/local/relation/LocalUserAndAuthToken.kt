package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalAuthToken
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalUserAndAuthToken(
    @Embedded val user: LocalUser,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
    )
    val authToken: LocalAuthToken
)
