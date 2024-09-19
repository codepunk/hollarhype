package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.util.consume

@Composable
fun AuthInitScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    // Attempt to consume result of silent authentication
    state.authenticatedUser.consume { authenticatedUser ->
        if (authenticatedUser.isLeft()) {
            onEvent(AuthEvent.NavigateToAuthOptions)
            return
        }
    }

    // If we made it here, silent authentication was successful
    val layoutMargin = layoutMargin()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = layoutMargin,
                end = layoutMargin
            )
    ) {
        Image(
            modifier = Modifier
                .widthIn(max = Size.HUGE.value)
                .fillMaxSize()
                .align(Alignment.Center),
            painter = painterResource(R.drawable.hh_logo),
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@ScreenPreviews
@Composable
fun AuthInitScreenPreviews() {
    HollarhypeTheme {
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
