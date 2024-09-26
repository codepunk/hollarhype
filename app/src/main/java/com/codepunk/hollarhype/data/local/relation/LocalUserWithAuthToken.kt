package com.codepunk.hollarhype.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.codepunk.hollarhype.data.local.entity.LocalAuthToken
import com.codepunk.hollarhype.data.local.entity.LocalUser

data class LocalUserWithAuthToken(
    @Embedded val user: LocalUser = LocalUser(),
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
    )
    val authTokens: Set<LocalAuthToken> = emptySet()
)
