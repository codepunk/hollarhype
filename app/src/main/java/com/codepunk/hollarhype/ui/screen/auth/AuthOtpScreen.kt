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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.OtpTextField
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.baseline
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonHeight
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.util.consume
import com.codepunk.hollarhype.util.http.HttpStatusException
import com.codepunk.hollarhype.util.http.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val OTP_LENGTH = 5

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
    val keyboardController = LocalSoftwareKeyboardController.current

    // Process any consumable (i.e. "single event") values
    state.verifyResult?.consume { value ->
        value.onRight {
            // If we get here, we successfully verified the OTP
            onEvent(AuthEvent.NavigateToLanding)
        }.onLeft { error ->
            when (error.cause) {
                is NoConnectivityException -> {
                    onEvent(AuthEvent.ConsumeVerifyResult)
                    LaunchedEffect(snackBarHostState) {
                        coroutineScope.launch(Dispatchers.Main) {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.error_no_internet_try_again)
                            )
                        }
                    }
                }
                !is HttpStatusException -> onEvent(AuthEvent.ConsumeVerifyResult)
            }
        }

        // At this point, verifyResult will be "consumed" if it was successful,
        // or if it was a NoConnectivityException,
        // or if it was NOT an HttpStatusException (that type will be consumed in
        // the alertDialog below).
    }

    val submitOtp: () -> Unit = {
        keyboardController?.hide()
        onEvent(
            AuthEvent.VerifyOtp(
                region = state.region,
                phoneNumber = state.phoneNumber,
                otp = state.otp
            )
        )
    }

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
                    modifier = Modifier
                        .padding(SizeLarge.value)
                        .widthIn(max = Size3xLarge.value),
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
                            submitOtp()
                        }
                         */
                    },
                    keyboardActions = KeyboardActions {
                        if (state.otp.length == OTP_LENGTH) {
                            submitOtp()
                        }
                    }
                )

                TextButton(
                    modifier = Modifier.padding(top = SizeMedium.value),
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

                Button(
                    modifier = Modifier
                        .width(standardButtonWidth)
                        .height(standardButtonHeight),
                    shape = RoundedCornerShape(size = buttonCornerRadius),
                    enabled = (!state.loading && state.otp.length == OTP_LENGTH),
                    onClick = submitOtp
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

    // We want to display an alertDialog if state.verifyResult exists,
    // it is an error, and its cause in an HttpStatusException
    state.verifyResult?.value?.onLeft { repositoryError ->
        if (repositoryError.cause is HttpStatusException) {
            AlertDialog(
                onDismissRequest = { onEvent(AuthEvent.ConsumeVerifyResult) },
                confirmButton = {
                    TextButton(
                        onClick = { onEvent(AuthEvent.ConsumeVerifyResult) }
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
        } else {
            onEvent(AuthEvent.ConsumeVerifyResult)
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
