package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.theme.layoutMarginWidth
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.ui.theme.xLargePadding

@Composable
fun AuthOptionsScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val sizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val layoutMargin = remember {
        layoutMarginWidth(sizeClass)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = layoutMargin,
                end = layoutMargin
            )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = xLargePadding,
                    end = xLargePadding
                )
                .aspectRatio(1f)
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = largePadding,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.hh_logo),
                contentDescription = stringResource(id = R.string.app_name)
            )

            Button(
                modifier = Modifier.width(standardButtonWidth),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                onClick = { onEvent(AuthEvent.NavigateToSignUp) }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up).lowercase(),
                    style = MaterialTheme.typography.displayMedium
                )
            }

            Button(
                modifier = Modifier.width(standardButtonWidth),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = { onEvent(AuthEvent.NavigateToSignIn) }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in).lowercase(),
                    style = MaterialTheme.typography.displayMedium
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
