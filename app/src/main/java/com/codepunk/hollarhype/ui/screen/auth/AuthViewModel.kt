package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        val getCredentialRequest = GetCredentialRequest(
            credentialOptions = listOf(GetPasswordOption())
        )
        authenticate()
    }

    // endregion Constructors

    // region Methods

    private fun navigateToAuthOptions() {
        state = state.copy(navigateToAuthOptions = true)
    }

    private fun navigateToSignUp() {

    }

    private fun navigateToSignIn() {

    }

    private fun authenticate() {
        viewModelScope.launch {
            // TODO Try to auto sign in here
            val success = false
            if (success) {
                // Navigate to landing (in main Navigation)
            } else {
                // Navigate to auth options
                //state = state.copy(navigateToAuthOptions = true)
                navigateToAuthOptions()
            }
        }
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.Initialize -> authenticate()
            AuthEvent.NavigateToAuthOptions -> navigateToAuthOptions()
            AuthEvent.NavigateToSignIn -> navigateToSignIn()
            AuthEvent.NavigateToSignUp -> navigateToSignUp()
        }
    }

    // endregion Methods

}
