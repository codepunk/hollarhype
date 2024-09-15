package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
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
        // TODO
    }

    private fun showSignUp() {
        // TODO
    }

    private fun showSignIn() {
        // TODO
    }

    private fun authenticate() {
        // TODO
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.Authenticate -> authenticate()
            AuthEvent.ShowAuthOptions -> showAuthOptions()
            AuthEvent.ShowSignIn -> showSignIn()
            AuthEvent.ShowSignUp -> showSignUp()
        }
    }

    // endregion Methods

}
