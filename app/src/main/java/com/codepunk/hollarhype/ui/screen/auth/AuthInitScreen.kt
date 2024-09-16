package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.util.responsiveLayoutWidth

@Composable
fun AuthInitScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    if (state.navigateToAuthOptions) {
        onEvent(AuthEvent.NavigateToAuthOptions)
    } else {
        val imageWidth = responsiveLayoutWidth(columns = 4)
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .width(imageWidth)
                    .padding(start = largePadding, end = largePadding)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.hh_logo),
                contentDescription = stringResource(id = R.string.app_name)
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun AuthInitScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun AuthInitScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}

@Preview(
    device = Devices.TABLET,
    showSystemUi = true
)
@Composable
fun AuthInitScreenPreviewTabletLight() {
    HollarhypeTheme(darkTheme = false) {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}
