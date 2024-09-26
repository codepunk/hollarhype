package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.OtpTextField
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnOtpChange
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnVerifyOtp
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonHeight
import com.codepunk.hollarhype.ui.theme.standardButtonWidth

@Composable
fun AuthOtpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }

    // Process any consumable (i.e. "single event") values
    //
    /*
    state.[loginResult]?.consume { value ->
    }
     */

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        val layoutMargin = layoutMargin().times(2)
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    start = layoutMargin,
                    end = layoutMargin
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = Size3xLarge.mid)
                    .fillMaxSize()
                    .padding(
                        bottom = SizeLarge.value
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(SizeMedium.value)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Text(
                    modifier = Modifier.padding(SizeLarge.value),
                    text = stringResource(id = R.string.enter_otp).uppercase(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                OtpTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    text = state.otp,
                    otpLength = 5,
                    onTextChange = { text, complete ->
                        onEvent(OnOtpChange(text, complete))
                    }
                )

                TextButton(
                    modifier = Modifier.padding(top = SizeMedium.value),
                    onClick = { onEvent(AuthEvent.OnResendOtp) }
                ) {
                    Text(
                        text = stringResource(id = R.string.resend_otp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Button(
                    modifier = Modifier
                        .width(standardButtonWidth)
                        .height(standardButtonHeight),
                    shape = RoundedCornerShape(size = buttonCornerRadius),
                    enabled = (!state.loading),
                    onClick = { 
                        onEvent(
                            OnVerifyOtp(otp = state.otp)
                        )
                    }
                ) {
                    if (state.loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.verify).lowercase(),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@ScreenPreviews
@Composable
fun AuthOtpPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthOtpScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}
