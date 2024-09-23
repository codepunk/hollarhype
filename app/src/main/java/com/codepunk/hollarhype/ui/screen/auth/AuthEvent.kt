package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.ui.component.Region

sealed interface AuthEvent {
    data object OnEditAvatar: AuthEvent
    data object OnRegisterNewPhoneNumber: AuthEvent
    data object OnSignUp: AuthEvent
    data object OnSignIn: AuthEvent
    data object OnResendOtp: AuthEvent
    data object NavigateToAuthOptions: AuthEvent
    data object NavigateToSignUp: AuthEvent
    data object NavigateToSignIn: AuthEvent
    data object Initialize: AuthEvent

    data class OnFirstNameChange(val firstName: String): AuthEvent
    data class OnLastNameChange(val lastName: String): AuthEvent
    data class OnEmailAddressChange(val emailAddress: String): AuthEvent
    data class OnRegionChange(val region: Region): AuthEvent
    data class OnPhoneNumberChange(val phoneNumber: String): AuthEvent
}
