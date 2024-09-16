package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val credentialManager: CredentialManager,
    private val repository: HollarhypeRepository
) : ViewModel() {

    // region Variables

    var state by mutableStateOf(AuthState())
        private set

    // endregion Variables

    // region Constructors

    init {
        authenticate()
    }

    // endregion Constructors

    // region Methods

    private fun showAuthOptions() {
        state = state.copy(mode = AuthState.Mode.SHOW_AUTH_OPTIONS)
    }

    private fun showSignUp() {
        state = state.copy(mode = AuthState.Mode.SHOW_SIGN_IN)
    }

    private fun showSignIn() {
        state = state.copy(mode = AuthState.Mode.SHOW_SIGN_IN)
    }

    private fun authenticate() {
        state = state.copy(mode = AuthState.Mode.INITIALIZING)

    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.Initialize -> authenticate()
            AuthEvent.ShowAuthOptions -> showAuthOptions()
            AuthEvent.ShowSignIn -> showSignIn()
            AuthEvent.ShowSignUp -> showSignUp()
        }
    }

    // endregion Methods

}
