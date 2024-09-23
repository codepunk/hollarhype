package com.codepunk.hollarhype.ui.screen.auth

import android.database.sqlite.SQLiteOutOfMemoryException
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.Option
import arrow.core.Some
import arrow.core.left
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.ui.component.Region
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

    private fun authenticate() {
        viewModelScope.launch {
            // TODO Try to auto sign in here
            val user = Eval.later {
                val authenticatedUser = IllegalStateException("No user").left()
                // If we got to this point, authenticated user was just "consumed"
                consumeAuthenticatedUser(authenticatedUser)
                authenticatedUser
            }
            state = state.copy(
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
            "AuthViewModel",
            "signUn: " + listOf(
                "user=$user"
            ).joinToString { it }
        )
    }

    private fun signIn(region: Region, phoneNumber: String) {
        state = state.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(
                phoneNumber = phoneNumber,
                region = region
            ).collect { result ->
                result.fold(
                    ifLeft = { throwable ->

                        // TODO NEXT
                        //  How do I show these error states?
                        //

                        Log.e(
                            this@AuthViewModel.javaClass.simpleName,
                            "signIn: ${throwable.message}"
                        )

                        state = state.copy(
                            isLoading = false
                        )
                    },
                    ifRight = { isSuccess ->
                        state = state.copy(
                            isLoading = false
                        )
                    }
                )
            }
        }
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

    private fun updateEmailAddress(emailAddress: String) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                emailAddress = emailAddress
            )
        )
    }

    private fun updateRegion(region: Region) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                region = region
            )
        )
    }

    private fun updatePhoneNumber(phoneNumber: String) {
        state = state.copy(
            authenticatingUser = state.authenticatingUser.copy(
                phoneNumber = phoneNumber
            )
        )
    }

    private fun consumeAuthenticatedUser(
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
                region = _stateFlow.value.authenticatingUser.region,
                phoneNumber = _stateFlow.value.authenticatingUser.phoneNumber
            )
            is AuthEvent.OnSignUp -> signUp(
                user = _stateFlow.value.authenticatingUser
            )
            is AuthEvent.OnNavigateToAuthOptions -> navigateToAuthOptions()
            is AuthEvent.OnNavigateToSignIn -> navigateToSignIn()
            is AuthEvent.OnNavigateToSignUp -> navigateToSignUp()
            is AuthEvent.OnFirstNameChange -> updateFirstName(event.firstName)
            is AuthEvent.OnLastNameChange -> updateLastName(event.lastName)
            is AuthEvent.OnEmailAddressChange -> updateEmailAddress(event.emailAddress)
            is AuthEvent.OnRegionChange -> updateRegion(event.region)
            is AuthEvent.OnPhoneNumberChange -> updatePhoneNumber(event.phoneNumber)
        }
    }

    // endregion Methods

}
