package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.datastore.entity.LocalAuthentication
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import com.codepunk.hollarhype.domain.model.Authentication

fun LocalAuthentication.toDomain(): Authentication = when (this) {
    is LocalAuthentication.LocalAuthenticated -> Authentication.Authenticated(
        userId = userId,
        authToken = authToken
    )
    LocalAuthentication.LocalUnauthenticated -> Authentication.Unauthenticated
}

fun RemoteAuthentication.toLocal(): LocalAuthentication.LocalAuthenticated =
    LocalAuthentication.LocalAuthenticated(
        userId = user.id,
        authToken = authToken
    )

fun RemoteAuthentication.toDomain(): Authentication.Authenticated =
    Authentication.Authenticated(
        userId = user.id,
        authToken = authToken
    )

fun Authentication.toLocal(): LocalAuthentication = when (this) {
    is Authentication.Authenticated -> LocalAuthentication.LocalAuthenticated(
        userId = userId,
        authToken = authToken
    )
    Authentication.Unauthenticated -> LocalAuthentication.LocalUnauthenticated
}
