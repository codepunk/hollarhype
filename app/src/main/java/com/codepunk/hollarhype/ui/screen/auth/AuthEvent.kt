package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.util.intl.Region

sealed interface AuthEvent {

    // Update data

    data class UpdateEmailAddress(val value: String): AuthEvent
    data class UpdateFirstName(val value: String): AuthEvent
    data class UpdateLastName(val value: String): AuthEvent
    data class UpdatePhoneNumber(val value: String): AuthEvent
    data class UpdateRegion(val value: Region): AuthEvent
    data class UpdateOtp(val value: String): AuthEvent

    // Navigation

    data object NavigateToSignIn : AuthEvent
    data object NavigateToSignUp : AuthEvent
    data object NavigateToOtp : AuthEvent
    data object NavigateToLanding : AuthEvent

    // User actions

    data object EditAvatar: AuthEvent
    data object RegisterNewPhoneNumber: AuthEvent
    data class SignUp(
        val firstName: String,
        val lastName: String,
        val emailAddress: String,
        val region: Region,
        val phoneNumber: String
    ): AuthEvent
    data class SignIn(
        val region: Region,
        val phoneNumber: String
    ): AuthEvent
    data object ResendOtp: AuthEvent
    data class VerifyOtp(
        val region: Region,
        val phoneNumber: String,
        val otp: String
    ): AuthEvent

}
