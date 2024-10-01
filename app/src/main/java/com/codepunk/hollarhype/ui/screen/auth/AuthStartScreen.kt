package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import arrow.core.getOrElse
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.domain.model.Unauthenticated
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.NavigateToSignIn
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.NavigateToSignUp
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonHeight
import com.codepunk.hollarhype.ui.theme.standardButtonWidth

@Composable
fun AuthStartScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    // TODO If auto-authenticating, show spinner ...

    var navigating by rememberSaveable { mutableStateOf(false) }

    // Do the following when verify result is "fresh"
    if (state.isAuthResultFresh) {
        onEvent(AuthEvent.ConsumeAuthResult)
        state.authResult?.run {
            val userSession = getOrElse { Unauthenticated }
            if (userSession is Authenticated) {
                navigating = true
                onEvent(AuthEvent.NavigateToLanding)
            }
            onEvent(AuthEvent.ClearAuthResult)
        }
    }

    val layoutMargin = layoutMargin()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = layoutMargin)
    ) {
        if (state.isLoading || navigating) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondaryContainer
            )
        } else {
            onEvent(AuthEvent.ClearAuthResult)
            Column(
                modifier = Modifier
                    .widthIn(max = Size3xLarge.mid)
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = SizeMedium.value,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.hh_logo),
                    contentDescription = stringResource(id = R.string.app_name)
                )

                Button(
                    modifier = Modifier
                        .width(standardButtonWidth)
                        .height(standardButtonHeight),
                    shape = RoundedCornerShape(size = buttonCornerRadius),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    onClick = { onEvent(NavigateToSignUp) }
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up).lowercase(),
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Button(
                    modifier = Modifier
                        .width(standardButtonWidth)
                        .height(standardButtonHeight),
                    shape = RoundedCornerShape(size = buttonCornerRadius),
                    onClick = { onEvent(NavigateToSignIn) }
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in).lowercase(),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@ScreenPreviews
@Composable
fun AuthOptionsPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthStartScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}
