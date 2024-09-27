package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.remote.entity.RemoteLoginResult
import com.codepunk.hollarhype.domain.model.LoginResult

fun RemoteLoginResult.toDomain(): LoginResult = LoginResult(success = success)
