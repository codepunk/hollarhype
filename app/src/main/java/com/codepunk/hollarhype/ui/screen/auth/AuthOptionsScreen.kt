package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.standardButtonWidth

@Composable
fun AuthOptionsScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val layoutMargin = layoutMargin()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = layoutMargin)
    ) {
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
                modifier = Modifier.width(standardButtonWidth),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                onClick = { onEvent(AuthEvent.OnGoToSignUp) }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up).lowercase(),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Button(
                modifier = Modifier.width(standardButtonWidth),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                onClick = { onEvent(AuthEvent.OnGoToSignIn) }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in).lowercase(),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@ScreenPreviews
@Composable
fun AuthOptionsPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthOptionsScreen(
                modifier = Modifier.padding(padding),
                state = AuthState(
                    authenticatedUser = Eval.now(User().right())
                )
            )
        }
    }
}
