package com.codepunk.hollarhype.manager

import androidx.datastore.core.DataStore
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.di.qualifier.ApplicationScope
import com.codepunk.hollarhype.di.qualifier.DefaultDispatcher
import com.codepunk.hollarhype.domain.model.Authentication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor(
    @ApplicationScope appCoroutineScope: CoroutineScope,
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    private val dataStore: DataStore<UserSettings>
) {
    init {
        appCoroutineScope.launch(defaultDispatcher) {
            dataStore.data.collect { userSettings ->
                when (val authentication = userSettings.authentication.toDomain()) {
                    is Authentication.Unauthenticated -> logout()
                    is Authentication.Authenticated -> login(authentication)
                }
            }
        }
    }

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
