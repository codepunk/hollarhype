package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.datastore.entity.LocalUserSession
import com.codepunk.hollarhype.data.datastore.entity.LocalAuthenticated
import com.codepunk.hollarhype.data.datastore.entity.LocalUnauthenticated
import com.codepunk.hollarhype.data.remote.entity.RemoteVerifyResult
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.domain.model.Unauthenticated

fun LocalUserSession.toDomain(): UserSession = when (this) {
    is LocalAuthenticated -> Authenticated(
        userId = userId,
        authToken = authToken
    )
    LocalUnauthenticated -> Unauthenticated
}

fun RemoteVerifyResult.toDomain(): UserSession =
    Authenticated(
        userId = user.id,
        authToken = authToken
    )

fun UserSession.toLocal(): LocalUserSession = when (this) {
    is Authenticated -> LocalAuthenticated(
        userId = userId,
        authToken = authToken
    )
    Unauthenticated -> LocalUnauthenticated
}
