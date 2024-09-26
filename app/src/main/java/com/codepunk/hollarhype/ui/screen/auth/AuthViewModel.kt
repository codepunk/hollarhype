package com.codepunk.hollarhype.ui.screen.auth

import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
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
        viewModelScope.launch(Dispatchers.IO) {
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
        state = state.copy(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                region = region
            ).collect { result ->
                state = state.copy(
                    loading = false,
                )
            }
        }
        state = state.copy(loading = false)
    }

    // Consuming values

    private fun resendOtp() {
        Log.d("AuthViewModel", "resendOtp")
    }

    fun onEvent(event: AuthEvent) {
        when (event) {

            // Data changes

            is AuthEvent.OnEmailAddressChange -> updateEmailAddress(event.value)
            is AuthEvent.OnFirstNameChange -> updateFirstName(event.value)
            is AuthEvent.OnLastNameChange -> updateLastName(event.value)
            is AuthEvent.OnPhoneNumberChange -> updatePhoneNumber(event.value)
            is AuthEvent.OnRegionChange -> updateRegion(event.value)
            is AuthEvent.OnOtpChange -> updateOtp(event.value)

            // Navigation

            // AuthNavigationEvents are handled up in AuthNavigation
            AuthEvent.OnNavigateToOtp -> { /* No op */ }
            AuthEvent.OnNavigateToSignIn -> { /* No op */ }
            AuthEvent.OnNavigateToSignUp -> { /* No op */ }

            // Read state

            // User actions

            AuthEvent.OnEditAvatar -> editAvatar()
            AuthEvent.OnRegisterNewPhoneNumber -> phoneNumberChanged()
            AuthEvent.OnResendOtp -> resendOtp()
            is AuthEvent.OnSignIn -> signIn(
                region = event.region,
                phoneNumber = event.phoneNumber
            )
            is AuthEvent.OnSignUp -> signUp(
                firstName = event.firstName,
                lastName = event.lastName,
                emailAddress = event.emailAddress,
                region = event.region,
                phoneNumber = event.phoneNumber
            )
            is AuthEvent.OnVerifyOtp -> verifyOtp(
                region = event.region,
                phoneNumber = event.phoneNumber,
                otp = event.otp
            )
        }
    }

    // endregion Methods

}
