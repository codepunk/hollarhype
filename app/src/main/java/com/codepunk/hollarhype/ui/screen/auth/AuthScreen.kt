package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.ui.component.HHTopAppBar
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HHTopAppBar()
        }
    ) { innerPadding ->
        AuthNavigation(
            paddingValues = innerPadding,
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
