package com.codepunk.hollarhype.ui.screen.auth

import android.content.res.Configuration
import android.util.Log
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
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.theme.responsiveColumnWidth
import com.codepunk.hollarhype.util.consume

@Composable
fun AuthInitScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val user = state.authenticatedUser.consume { authenticatedUser ->
        if (authenticatedUser.isLeft()) {
            onEvent(AuthEvent.NavigateToAuthOptions)
        }
    }
    // Silent authentication was successful
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .width(responsiveColumnWidth(columnSpan = 4))
                .padding(start = largePadding, end = largePadding)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.hh_logo),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AuthInitScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState(
                    authenticatedUser = Eval.now(User().right())
                )
            )
        }
    }
}

@Preview
@Composable
fun AuthInitScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState(
                    authenticatedUser = Eval.now(User().right())
                )
            )
        }
    }
}

@Preview(
    device = Devices.TABLET
)
@Composable
fun AuthInitScreenPreviewTabletLight() {
    HollarhypeTheme(darkTheme = false) {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState(
                    authenticatedUser = Eval.now(User().right())
                )
            )
        }
    }
}
