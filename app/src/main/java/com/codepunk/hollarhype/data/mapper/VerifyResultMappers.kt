package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.datastore.entity.LocalVerifyResult
import com.codepunk.hollarhype.data.remote.entity.RemoteVerifyResult

fun RemoteVerifyResult.toLocal(): LocalVerifyResult = LocalVerifyResult(
    userId = user.id,
    authToken = authToken
)
