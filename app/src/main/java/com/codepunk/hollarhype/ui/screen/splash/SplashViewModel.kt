package com.codepunk.hollarhype.ui.screen.splash

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.di.qualifier.IoDispatcher
import com.codepunk.hollarhype.domain.model.Unauthenticated
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.manager.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: HollarhypeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val dataStore: DataStore<UserSettings>,
    private val userSessionManager: UserSessionManager
): ViewModel() {

    private val isDelaying: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val isAuthenticating: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading = isDelaying.combine(isAuthenticating) { isDelaying, isAuthenticating ->
        isDelaying || isAuthenticating
    }.stateIn(viewModelScope, SharingStarted.Eagerly, true)

    init {
        viewModelScope.launch {
            delay(750)
            isDelaying.value = false
        }
        authenticate()
    }

    private fun authenticate() {
        viewModelScope.launch(ioDispatcher) {
            val userSession = runBlocking {
                dataStore.data.firstOrNull()?.userSession?.toDomain() ?: Unauthenticated
            }
            userSessionManager.update(userSession)
            repository.authenticate().collect { _ -> isAuthenticating.value = false }
        }
    }

}