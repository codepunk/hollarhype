package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.datastore.entity.LocalVerifyResult
import com.codepunk.hollarhype.data.remote.entity.RemoteVerifyResult
import com.codepunk.hollarhype.domain.model.VerifyResult

fun RemoteVerifyResult.toLocal(): LocalVerifyResult = LocalVerifyResult(
    userId = user.id,
    authToken = authToken
)

fun RemoteVerifyResult.toDomain(): VerifyResult = VerifyResult(
    user = user.toDomain(),
    authToken = authToken
)
