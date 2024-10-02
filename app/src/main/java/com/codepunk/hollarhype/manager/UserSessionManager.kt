package com.codepunk.hollarhype.manager

import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.domain.model.Unauthenticated
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor() {

    private val _userSession: MutableStateFlow<UserSession> =
        MutableStateFlow(Unauthenticated)

    val userSession: StateFlow<UserSession> = _userSession.asStateFlow()

    fun login(userSession: Authenticated) {
        _userSession.value = userSession
    }

    fun logout() {
        _userSession.value = Unauthenticated
    }

    fun update(userSession: UserSession) {
        _userSession.value = userSession
    }

    fun isLoggedIn(): Boolean = userSession.value is Authenticated

}
