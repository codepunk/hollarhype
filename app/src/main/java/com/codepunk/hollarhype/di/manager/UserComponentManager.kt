package com.codepunk.hollarhype.di.manager

import com.codepunk.hollarhype.di.component.UserComponent
import com.codepunk.hollarhype.di.qualifier.ApplicationScope
import com.codepunk.hollarhype.di.qualifier.DefaultDispatcher
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.manager.UserSessionManager
import dagger.hilt.internal.GeneratedComponentManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * See https://myungpyo.medium.com/using-hilt-custom-component-c8227d89aed6
 */

@Singleton
class UserComponentManager @Inject constructor(
    @ApplicationScope appCoroutineScope: CoroutineScope,
    private val userSessionManager: UserSessionManager,
    private val userComponentProvider: Provider<UserComponent.Builder>,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : GeneratedComponentManager<UserComponent> {
    private val _versionState: MutableStateFlow<ComponentVersion> =
        MutableStateFlow(ComponentVersion.next())
    val versionState: StateFlow<ComponentVersion> = _versionState.asStateFlow()

    private var userComponent: UserComponent = userComponentProvider.get().build()

    private var lastAuthentication: Authentication = userSessionManager.authentication.value

    init {
        appCoroutineScope.launch(defaultDispatcher) {
            userSessionManager.authentication.collect { authentication ->
                if (lastAuthentication == authentication) {
                    return@collect
                }
                lastAuthentication = authentication
                rebuildComponent()
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    suspend fun rebuildComponent() {
        userComponent = userComponentProvider.get().build()
        _versionState.emit(ComponentVersion.next())
    }

    override fun generatedComponent(): UserComponent = userComponent

    companion object {

        data class ComponentVersion internal constructor(
            private val version: Int = versionSeq.incrementAndGet()
        ) {
            companion object {
                private val versionSeq = AtomicInteger(0)

                fun next(): ComponentVersion = ComponentVersion()
            }
        }

    }
}
