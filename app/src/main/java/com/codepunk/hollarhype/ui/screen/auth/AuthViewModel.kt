package com.codepunk.hollarhype.ui.screen.auth

import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.eval.Eval
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.DataChange
import com.codepunk.hollarhype.util.intl.Region
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val credentialManager: CredentialManager,
    private val repository: HollarhypeRepository
) : ViewModel() {

    // region Variables

    // We use StateFlow here instead of State/mutableStateOf to keep Compose-related
    // constructs out of ViewModel
    private val _stateFlow: MutableStateFlow<AuthState> = MutableStateFlow(AuthState())
    val stateFlow = _stateFlow.asStateFlow()

    private var state: AuthState
        get() = _stateFlow.value
        set(value) { _stateFlow.value = value }

    // endregion Variables

    // region Constructors

    init {
        val getCredentialRequest = GetCredentialRequest(
            credentialOptions = listOf(GetPasswordOption())
        )

        // TODO Authenticate w/credentials

        authenticate()
    }

    // endregion Constructors

    // region Methods

    // Data changes

    private fun updateEmailAddress(emailAddress: String) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                emailAddress = emailAddress
            )
        )
    }

    private fun updateFirstName(firstName: String) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                firstName = firstName
            )
        )
    }

    private fun updateLastName(lastName: String) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                lastName = lastName
            )
        )
    }

    private fun updatePhoneNumber(phoneNumber: String) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                phoneNumber = phoneNumber
            ),
            phoneNumberError = ""
        )
    }

    private fun updateRegion(region: Region) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                region = region
            )
        )
    }

    // Navigation

    // User actions

    private fun authenticate() {
        viewModelScope.launch {
            // TODO Try to auto sign in here
        }
    }

    private fun editAvatar() {
        Log.d("AuthViewModel", "editAvatar")
    }

    private fun phoneNumberChanged() {
        Log.d("AuthViewModel", "phoneNumberChanged")
    }

    private fun signUp(user: User) {
        Log.d(
            "AuthViewModel",
            "signUn: " + listOf(
                "user=$user"
            ).joinToString { it }
        )
    }

    private fun signIn(region: Region, phoneNumber: String) {
        state = state.copy(
            loading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(
                phoneNumber = phoneNumber,
                region = region
            ).collect { result ->
                state = state.copy(
                    loading = false,
                    loginResult = Eval.later { result },
                    phoneNumberError = result.leftOrNull()?.errors?.getOrNull(0) ?: ""
                )
            }
        }
    }

    // Consuming values

    private fun resendOtp() {
        Log.d("AuthViewModel", "resendOtp")
    }

    fun onEvent(event: AuthEvent) {
        when (event) {

            // Data changes

            is DataChange.OnEmailAddressChange -> updateEmailAddress(event.value)
            is DataChange.OnFirstNameChange -> updateFirstName(event.value)
            is DataChange.OnLastNameChange -> updateLastName(event.value)
            is DataChange.OnPhoneNumberChange -> updatePhoneNumber(event.value)
            is DataChange.OnRegionChange -> updateRegion(event.value)

            // Navigation

            // AuthNavigationEvents are handled up in AuthNavigation
            is AuthEvent.NavigationEvent -> { /* No op */ }

            // Read state

            // User actions

            is AuthEvent.OnEditAvatar -> editAvatar()
            is AuthEvent.OnRegisterNewPhoneNumber -> phoneNumberChanged()
            is AuthEvent.OnResendOtp -> resendOtp()
            is AuthEvent.OnSignIn -> signIn(
                region = _stateFlow.value.authenticatingUser.region,
                phoneNumber = _stateFlow.value.authenticatingUser.phoneNumber
            )
            is AuthEvent.OnSignUp -> signUp(
                user = _stateFlow.value.authenticatingUser
            )

        }
    }

    // endregion Methods

}
