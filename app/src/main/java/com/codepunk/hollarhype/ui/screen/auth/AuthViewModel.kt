package com.codepunk.hollarhype.ui.screen.auth

import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.left
import arrow.eval.Eval
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.ui.component.Region
import com.codepunk.hollarhype.util.getFullPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun authenticate() {
        viewModelScope.launch {
            // TODO Try to auto sign in here
            val user = Eval.later {
                val authenticatedUser = IllegalStateException("No user").left()
                // If we got to this point, authenticated user was just "consumed"
                onConsumeAuthenticatedUser(authenticatedUser)
                authenticatedUser
            }
            _stateFlow.value = _stateFlow.value.copy(
                authenticatedUser = user
            )
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
            "AuthViewModel", buildString {
                append("signUp: ")
                append("authenticatingUser=${_stateFlow.value.authenticatingUser}")
            }
        )
    }

    private fun signIn(fullPhoneNumber: String) {
        Log.d(
            "AuthViewModel", buildString {
                append("signIn: ")
                append("fullPhoneNumber=$fullPhoneNumber")
            }
        )
    }

    private fun resendOtp() {
        Log.d("AuthViewModel", "resendOtp")
    }

    private fun navigateToAuthOptions() {

    }

    private fun navigateToSignUp() {

    }

    private fun navigateToSignIn() {

    }

    private fun updateFirstName(firstName: String) {
        with(_stateFlow) {
            value = value.copy(
                authenticatingUser = value.authenticatingUser.copy(
                    firstName = firstName
                )
            )
        }
    }

    private fun updateLastName(lastName: String) {
        with(_stateFlow) {
            value = value.copy(
                authenticatingUser = value.authenticatingUser.copy(
                    lastName = lastName
                )
            )
        }
    }

    private fun updateEmailAddress(emailAddress: String) {
        with(_stateFlow) {
            value = value.copy(
                authenticatingUser = value.authenticatingUser.copy(
                    emailAddress = emailAddress
                )
            )
        }
    }

    private fun updateRegion(region: Region) {
        with(_stateFlow) {
            value = value.copy(
                authenticatingUser = value.authenticatingUser.copy(
                    region = region
                )
            )
        }
    }

    private fun updatePhoneNumber(phoneNumber: String) {
        with(_stateFlow) {
            value = value.copy(
                authenticatingUser = value.authenticatingUser.copy(
                    phoneNumber = phoneNumber
                )
            )
        }
    }

    private fun onConsumeAuthenticatedUser(
        authenticatedUser: Either<Throwable, User>
    ) {
        // Authenticated user was just consumed, setting it to an Eval.Now
        // will mark it as consumed
        _stateFlow.value = _stateFlow.value.copy(
            authenticatedUser = Eval.now(authenticatedUser),
        )
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnEditAvatar -> editAvatar()
            is AuthEvent.OnRegisterNewPhoneNumber -> phoneNumberChanged()
            is AuthEvent.OnResendOtp -> resendOtp()
            is AuthEvent.OnSignIn -> signIn(
                _stateFlow.value.authenticatingUser.getFullPhoneNumber()
            )
            is AuthEvent.OnSignUp -> signUp(
                _stateFlow.value.authenticatingUser
            )
            is AuthEvent.Initialize -> authenticate()
            is AuthEvent.NavigateToAuthOptions -> navigateToAuthOptions()
            is AuthEvent.NavigateToSignIn -> navigateToSignIn()
            is AuthEvent.NavigateToSignUp -> navigateToSignUp()
            is AuthEvent.OnFirstNameChange -> updateFirstName(event.firstName)
            is AuthEvent.OnLastNameChange -> updateLastName(event.lastName)
            is AuthEvent.OnEmailAddressChange -> updateEmailAddress(event.emailAddress)
            is AuthEvent.OnRegionChange -> updateRegion(event.region)
            is AuthEvent.OnPhoneNumberChange -> updatePhoneNumber(event.phoneNumber)
        }
    }

    // endregion Methods

}
