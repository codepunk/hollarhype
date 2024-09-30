package com.codepunk.hollarhype.ui.screen.auth

import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codepunk.hollarhype.di.qualifier.DefaultDispatcher
import com.codepunk.hollarhype.di.qualifier.IoDispatcher
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.manager.UserSessionManager
import com.codepunk.hollarhype.util.intl.Region
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: HollarhypeRepository,
    private val credentialManager: CredentialManager,
    private val userSessionManager: UserSessionManager,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
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

        // TODO TEMP
        viewModelScope.launch(defaultDispatcher) {
            userSessionManager.authentication.collect { authentication ->
                Log.d(
                    this@AuthViewModel.javaClass.simpleName,
                    "authentication=$authentication"
                )
            }
        }

        authenticate()
    }

    // endregion Constructors

    // region Methods

    // Data changes

    private fun updateEmailAddress(emailAddress: String) {
        state = state.copy(
            emailAddress = emailAddress
        )
    }

    private fun updateFirstName(firstName: String) {
        state = state.copy(
            firstName = firstName
        )
    }

    private fun updateLastName(lastName: String) {
        state = state.copy(
            lastName = lastName
        )
    }

    private fun updatePhoneNumber(phoneNumber: String) {
        state = state.copy(
            phoneNumber = phoneNumber,
            phoneNumberError = ""
        )
    }

    private fun updateRegion(region: Region) {
        state = state.copy(
            region = region
        )
    }

    private fun updateOtp(otp: String) {
        state = state.copy(
            otp = otp
        )
    }

    // Navigation

    // User actions

    private fun authenticate() {
        viewModelScope.launch(ioDispatcher) {
            // TODO Try to auto sign in here
        }
    }

    private fun editAvatar() {
        Log.d("AuthViewModel", "editAvatar")
    }

    private fun phoneNumberChanged() {
        Log.d("AuthViewModel", "phoneNumberChanged")
    }

    private fun signUp(
        firstName: String,
        lastName: String,
        emailAddress: String,
        region: Region,
        phoneNumber: String
    ) {
        Log.d(
            "AuthViewModel",
            "signUn: " + listOf(
                "firstName=$firstName",
                "lastName=$lastName",
                "emailAddress=$emailAddress",
                "region=$region",
                "phoneNumber=$phoneNumber"
            ).joinToString { it }
        )
    }

    private fun signIn(region: Region, phoneNumber: String) {
        state = state.copy(loading = true)
        viewModelScope.launch(ioDispatcher) {
            repository.login(
                phoneNumber = phoneNumber,
                region = region
            ).collect { result ->
                state = state.copy(
                    loading = false,
                    loginResult = lazy { result },
                    phoneNumberError = result.leftOrNull()?.errors?.getOrNull(0) ?: ""
                )
            }
        }
    }

    private fun verifyOtp(
        region: Region,
        phoneNumber: String,
        otp: String
    ) {
        state = state.copy(loading = true)
        viewModelScope.launch(ioDispatcher) {
            repository.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                region = region
            ).collect { result ->
                state = state.copy(
                    loading = false,
                    verifyResult = lazy { result }
                )
            }
        }
    }

    private fun resendOtp() {
        Log.d("AuthViewModel", "resendOtp")
    }

    // Consuming values

    private fun consumeLoginResult() {
        state = state.copy(
            loginResult = null
        )
    }

    private fun consumeVerifyResult() {
        state = state.copy(
            verifyResult = null
        )
    }

    fun onEvent(event: AuthEvent) {
        when (event) {

            // Data changes

            is AuthEvent.UpdateEmailAddress -> updateEmailAddress(event.value)
            is AuthEvent.UpdateFirstName -> updateFirstName(event.value)
            is AuthEvent.UpdateLastName -> updateLastName(event.value)
            is AuthEvent.UpdatePhoneNumber -> updatePhoneNumber(event.value)
            is AuthEvent.UpdateRegion -> updateRegion(event.value)
            is AuthEvent.UpdateOtp -> updateOtp(event.value)

            // Navigation

            // AuthNavigationEvents are propagated up to AuthNavigation
            // rather than being handled here
            AuthEvent.NavigateToOtp -> { /* No op */ }
            AuthEvent.NavigateToSignIn -> { /* No op */ }
            AuthEvent.NavigateToSignUp -> { /* No op */ }
            AuthEvent.NavigateToLanding -> { /* No op */ }

            // Consume one-time events

            AuthEvent.ConsumeLoginResult -> consumeLoginResult()
            AuthEvent.ConsumeVerifyResult -> consumeVerifyResult()

            // User actions

            AuthEvent.EditAvatar -> editAvatar()
            AuthEvent.RegisterNewPhoneNumber -> phoneNumberChanged()
            AuthEvent.ResendOtp -> resendOtp()
            is AuthEvent.SignIn -> signIn(
                region = event.region,
                phoneNumber = event.phoneNumber
            )
            is AuthEvent.SignUp -> signUp(
                firstName = event.firstName,
                lastName = event.lastName,
                emailAddress = event.emailAddress,
                region = event.region,
                phoneNumber = event.phoneNumber
            )
            is AuthEvent.VerifyOtp -> verifyOtp(
                region = event.region,
                phoneNumber = event.phoneNumber,
                otp = event.otp
            )

        }
    }

    // endregion Methods

}
