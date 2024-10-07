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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.common.showErrorSnackBar
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.component.HypeButton
import com.codepunk.hollarhype.ui.component.OtpTextField
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.baseline
import com.codepunk.hollarhype.ui.theme.hypeButtonHeight
import com.codepunk.hollarhype.ui.theme.hypeButtonWidth
import com.codepunk.hollarhype.ui.theme.sizes
import com.codepunk.hollarhype.ui.theme.util.layoutMargin
import com.codepunk.hollarhype.util.http.HttpStatusException

const val OTP_LENGTH = 5

@Composable
fun AuthOtpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Do the following when verify result is "fresh"
    if (state.isVerifyResultFresh) {
        onEvent(AuthEvent.ConsumeVerifyResult)
        state.verifyResult?.run {
            onLeft { error ->
                if (error.cause !is HttpStatusException) {
                    // HttpStatusExceptions will be handled differently
                    showErrorSnackBar(
                        cause = error.cause,
                        context = LocalContext.current,
                        snackBarHostState = snackBarHostState,
                        coroutineScope = coroutineScope
                    )
                    onEvent(AuthEvent.ClearVerifyResult)
                }
            }.onRight {
                // If we get here, we successfully verified the OTP
                onEvent(AuthEvent.ConsumeVerifyResult)
                onEvent(AuthEvent.NavigateToLanding)
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HollarHypeTopAppBar(
                onNavigateUp = { onEvent(AuthEvent.NavigateUp) }
            )
        },
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
                    .widthIn(max = sizes.regionXLarge)
                    .fillMaxSize()
                    .padding(
                        bottom = sizes.paddingXLarge
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(sizes.paddingLarge)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Text(
                    modifier = Modifier
                        .padding(sizes.paddingXLarge)
                        .widthIn(max = sizes.regionLarge),
                    text = stringResource(id = R.string.enter_otp).uppercase(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                OtpTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    text = state.otp,
                    otpLength = OTP_LENGTH,
                    onTextChange = { text, _ ->
                        onEvent(AuthEvent.UpdateOtp(text))

                        // TODO Not sure about this yet
                        /*
                        if (complete) {
                            onEvent(
                                AuthEvent.VerifyOtp(
                                    region = state.region,
                                    phoneNumber = state.phoneNumber,
                                    otp = state.otp
                                )
                            )
                        }
                         */
                    },
                    keyboardActions = KeyboardActions {
                        if (state.otp.length == OTP_LENGTH) {
                            keyboardController?.hide()
                            onEvent(
                                AuthEvent.VerifyOtp(
                                    region = state.region,
                                    phoneNumber = state.phoneNumber,
                                    otp = state.otp
                                )
                            )
                        }
                    }
                )

                TextButton(
                    modifier = Modifier.padding(top = sizes.paddingLarge),
                    onClick = { onEvent(AuthEvent.ResendOtp) }
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

                HypeButton(
                    modifier = Modifier
                        .width(hypeButtonWidth)
                        .height(hypeButtonHeight),
                    enabled = (!state.isLoading && state.otp.length == OTP_LENGTH),
                    onClick = {
                        onEvent(
                            AuthEvent.VerifyOtp(
                                region = state.region,
                                phoneNumber = state.phoneNumber,
                                otp = state.otp
                            )
                        )
                    }
                ) {
                    if (state.isLoading) {
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

    // We want to display an alertDialog if state.verifyResult exists,
    // it is an error, and its cause in an HttpStatusException
    state.verifyResult?.onLeft { repositoryError ->
        if (repositoryError.cause is HttpStatusException) {
            AlertDialog(
                onDismissRequest = { onEvent(AuthEvent.ClearVerifyResult) },
                confirmButton = {
                    TextButton(
                        onClick = { onEvent(AuthEvent.ClearVerifyResult) }
                    ) {
                        Text(
                            style = baseline.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            text = stringResource(id = android.R.string.ok)
                        )
                    }
                },
                title = {
                    Text(
                        style = baseline.titleMedium,
                        text = stringResource(id = R.string.error)
                    )
                },
                text = {
                    Text(
                        text = repositoryError.errors.getOrElse(0) {
                            stringResource(id = R.string.error_unknown)
                        }
                    )
                }
            )
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
