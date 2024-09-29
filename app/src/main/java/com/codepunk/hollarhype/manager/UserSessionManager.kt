package com.codepunk.hollarhype.manager

import com.codepunk.hollarhype.domain.model.Authentication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor() {

    private val _authentication: MutableStateFlow<Authentication> =
        MutableStateFlow(Authentication.Unauthenticated)

    val authentication: StateFlow<Authentication> = _authentication.asStateFlow()

    fun login(authentication: Authentication.Authenticated) {
        _authentication.tryEmit(authentication)
    }

    fun logout() {
        _authentication.tryEmit(Authentication.Unauthenticated)
    }

    fun isLoggedIn(): Boolean = authentication.value.authenticated

}
