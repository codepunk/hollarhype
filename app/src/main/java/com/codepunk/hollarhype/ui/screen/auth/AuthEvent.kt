package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.util.intl.Region

sealed interface AuthEvent {

    // Data changes

    data class OnEmailAddressChange(val value: String): AuthEvent
    data class OnFirstNameChange(val value: String): AuthEvent
    data class OnLastNameChange(val value: String): AuthEvent
    data class OnPhoneNumberChange(val value: String): AuthEvent
    data class OnRegionChange(val value: Region): AuthEvent
    data class OnOtpChange(val value: String): AuthEvent

    // Navigation

    data object OnNavigateToSignIn : AuthEvent
    data object OnNavigateToSignUp : AuthEvent
    data object OnNavigateToOtp : AuthEvent

    // User actions

    data object OnEditAvatar: AuthEvent
    data object OnRegisterNewPhoneNumber: AuthEvent
    data class OnSignUp(
        val firstName: String,
        val lastName: String,
        val emailAddress: String,
        val region: Region,
        val phoneNumber: String
    ): AuthEvent
    data class OnSignIn(
        val region: Region,
        val phoneNumber: String
    ): AuthEvent
    data object OnResendOtp: AuthEvent
    data class OnVerifyOtp(
        val region: Region,
        val phoneNumber: String,
        val otp: String
    ): AuthEvent

}
