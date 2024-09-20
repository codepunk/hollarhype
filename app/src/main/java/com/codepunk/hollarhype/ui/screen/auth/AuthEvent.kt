package com.codepunk.hollarhype.ui.screen.auth

sealed interface AuthEvent {
    data object OnEditAvatar: AuthEvent
    data object OnSignIn: AuthEvent
    data object OnResendOtp: AuthEvent
    data object NavigateToAuthOptions: AuthEvent
    data object NavigateToSignUp: AuthEvent
    data object NavigateToSignIn: AuthEvent
    data object Initialize: AuthEvent
}
