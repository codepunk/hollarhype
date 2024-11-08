package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
    ) { _ ->
        AuthNavigation(
            state = state,
            onEvent = onEvent
        )
    }
}

@Preview
@Composable
fun AuthScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        AuthScreen(
            state = AuthState()
        )
    }
}

@Preview
@Composable
fun AuthScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        AuthScreen(
            state = AuthState()
        )
    }
}
