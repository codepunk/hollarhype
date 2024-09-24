package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun AuthOtpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    Text(text = "One-time password")
}

@ScreenPreviews
@Composable
fun AuthOtpPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthOtpScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}
