package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.ui.component.Region

sealed interface AuthEvent {

    // Data changes

    data class OnEmailAddressChange(val emailAddress: String): AuthEvent
    data class OnFirstNameChange(val firstName: String): AuthEvent
    data class OnLastNameChange(val lastName: String): AuthEvent
    data class OnPhoneNumberChange(val phoneNumber: String): AuthEvent
    data class OnRegionChange(val region: Region): AuthEvent

    // Navigation

    data object OnNavigateToAuthOptions: AuthEvent
    data object OnNavigateToSignIn: AuthEvent
    data object OnNavigateToSignUp: AuthEvent

    // Actions

    data object OnEditAvatar: AuthEvent
    data object OnRegisterNewPhoneNumber: AuthEvent
    data object OnSignUp: AuthEvent
    data object OnSignIn: AuthEvent
    data object OnResendOtp: AuthEvent

}
