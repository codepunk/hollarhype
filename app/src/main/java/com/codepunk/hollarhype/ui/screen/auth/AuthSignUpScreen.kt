package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.theme.xLargePadding

@Composable
fun AuthSignUpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = largePadding, end = largePadding),
            painter = painterResource(R.drawable.img_default_user),
            contentDescription = stringResource(id = R.string.app_name)
        )

        Spacer(modifier = Modifier.height(xLargePadding))

        TextField(
            value = "",
            label = {
                Text(
                    text = stringResource(id = R.string.first_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        Spacer(modifier = Modifier.height(largePadding))

        TextField(
            value = "",
            label = {
                Text(
                    text = stringResource(id = R.string.last_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        Spacer(modifier = Modifier.height(largePadding))

        TextField(
            value = "",
            label = {
                Text(
                    text = stringResource(id = R.string.email_address),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        Spacer(modifier = Modifier.height(largePadding))

        Row {
            Button(
                modifier = Modifier,
                shape = RoundedCornerShape(size = buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = { onEvent(AuthEvent.NavigateToSignIn) }
            ) {
                Text(
                    text = "+1",
                    style = MaterialTheme.typography.displaySmall
                )
            }

            TextField(
                value = "",
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                onValueChange = { /* No op */ }
            )
        }

        Spacer(modifier = Modifier.height(largePadding))

        Text(
            text = stringResource(id = R.string.disclaimer),
            style = MaterialTheme.typography.labelSmall
        )

        val buttonWidth = 200.dp

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

@ScreenPreviews
@Composable
fun AuthSignUpPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthSignUpScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}
