package com.codepunk.hollarhype.ui.screen.auth

data class AuthState(
    val mode: Mode = Mode.INITIAL
) {
    enum class Mode {
        INITIAL
    }
}
