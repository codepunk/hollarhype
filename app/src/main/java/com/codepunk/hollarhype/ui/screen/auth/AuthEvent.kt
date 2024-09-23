package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.ui.component.Region

sealed interface AuthEvent {

    // Data changes

    data class OnEmailAddressChange(val emailAddress: String): AuthEvent
    data class OnFirstNameChange(val firstName: String): AuthEvent
    data class OnLastNameChange(val lastName: String): AuthEvent
    data class OnPhoneNumberChange(val phoneNumber: String): AuthEvent
    data class OnRegionChange(val region: Region): AuthEvent

    // Actions

    data object GoToAuthOptions: AuthEvent
    data object GoToSignIn: AuthEvent
    data object GoToSignUp: AuthEvent
    data object OnEditAvatar: AuthEvent
    data object OnRegisterNewPhoneNumber: AuthEvent
    data object OnSignUp: AuthEvent
    data object OnSignIn: AuthEvent
    data object OnResendOtp: AuthEvent

}
