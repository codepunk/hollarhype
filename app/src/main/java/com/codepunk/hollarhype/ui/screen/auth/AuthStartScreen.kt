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
import com.codepunk.hollarhype.ui.component.HypeButton
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.NavigateToSignIn
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.NavigateToSignUp
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LocalFixedColors
import com.codepunk.hollarhype.ui.theme.LocalSizes
import com.codepunk.hollarhype.ui.theme.util.layoutMargin

@Composable
fun AuthStartScreen(
    modifier: Modifier = Modifier,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val sizes = LocalSizes.current

    val layoutMargin = layoutMargin()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = layoutMargin)
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = sizes.region2xLarge)
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

            HypeButton(
                modifier = Modifier
                    .width(sizes.region)
                    .height(sizes.component),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalFixedColors.current.primaryFixed,
                    contentColor = LocalFixedColors.current.onPrimaryFixed
                ),
                onClick = { onEvent(NavigateToSignUp) }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up).lowercase(),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            HypeButton(
                modifier = Modifier
                    .width(sizes.region)
                    .height(sizes.component),
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
