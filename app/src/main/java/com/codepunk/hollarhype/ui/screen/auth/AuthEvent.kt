package com.codepunk.hollarhype.ui.screen.auth

sealed interface AuthEvent {
    data object OnEditAvatar: AuthEvent
    data object OnPhoneNumberChanged: AuthEvent
    data class OnSignUp(
        val firstName: String,
        val lastName: String,
        val emailAddress: String,
        val countryCode: Int,
        val phoneNumber: String,
        val avatar: Any? = null
    ): AuthEvent
    data class OnSignIn(
        val countryCode: Int,
        val phoneNumber: String,
        val otp: Int? = null
    ): AuthEvent
    data object OnResendOtp: AuthEvent
    data object NavigateToAuthOptions: AuthEvent
    data object NavigateToSignUp: AuthEvent
    data object NavigateToSignIn: AuthEvent
    data object Initialize: AuthEvent
}
