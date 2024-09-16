package com.codepunk.hollarhype.ui.screen.auth

sealed interface AuthEvent {
    data object ShowAuthOptions: AuthEvent
    data object ShowSignUp: AuthEvent
    data object ShowSignIn: AuthEvent
    data object Initialize: AuthEvent
}