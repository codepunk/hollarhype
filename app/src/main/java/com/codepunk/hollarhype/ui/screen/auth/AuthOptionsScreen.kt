package com.codepunk.hollarhype.ui.screen.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.theme.responsiveColumnWidth

@Composable
fun AuthOptionsScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = largePadding,
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            modifier = Modifier
                .width(responsiveColumnWidth(columnSpan = 4))
                .padding(start = largePadding, end = largePadding),
            painter = painterResource(R.drawable.hh_logo),
            contentDescription = stringResource(id = R.string.app_name)
        )

        val buttonWidth = responsiveColumnWidth(columnSpan = 2)

        Button(
            modifier = Modifier
                .width(buttonWidth),
            shape = RoundedCornerShape(size = buttonCornerRadius),
            onClick = { onEvent(AuthEvent.NavigateToSignUp) }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up).lowercase(),
                style = MaterialTheme.typography.displayMedium
            )
        }

        Button(
            modifier = Modifier
                .width(buttonWidth),
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

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AuthOptionsScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
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

@Preview
@Composable
fun AuthOptionsScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
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

@Preview(
    device = Devices.TABLET
)
@Composable
fun AuthOptionsScreenPreviewTabletLight() {
    HollarhypeTheme(darkTheme = false) {
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
