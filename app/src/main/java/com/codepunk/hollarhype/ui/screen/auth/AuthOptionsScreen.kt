package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthOptionsScreen(
    modifier: Modifier = Modifier,
    stateFlow: StateFlow<AuthState>,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val state = stateFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Greeting(name = "Options")
    }
}
