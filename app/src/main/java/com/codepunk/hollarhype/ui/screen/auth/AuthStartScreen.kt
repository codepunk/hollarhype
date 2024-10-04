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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.NavigateToSignIn
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.NavigateToSignUp
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.sizes
import com.codepunk.hollarhype.ui.theme.util.layoutMargin

@Composable
fun AuthStartScreen(
    modifier: Modifier = Modifier,
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
                .widthIn(max = sizes.regionXLarge)
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = sizes.paddingLarge,
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
                    .width(sizes.regionMedium)
                    .height(sizes.componentMedium),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
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
                    .width(sizes.regionMedium)
                    .height(sizes.componentMedium),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                    contentColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
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

@ScreenPreviews
@Composable
fun AuthOptionsPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthStartScreen(
                modifier = Modifier.padding(padding)
            )
        }
    }
}
