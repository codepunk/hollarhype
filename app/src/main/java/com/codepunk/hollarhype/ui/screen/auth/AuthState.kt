package com.codepunk.hollarhype.ui.screen.auth

data class AuthState(
    val mode: Mode = Mode.INITIAL
) {
    enum class Mode {
        INITIAL,
        AUTHENTICATING,
        SHOW_AUTH_OPTIONS,
        SHOW_SIGN_UP,
        SHOW_SIGN_IN
    }
}
