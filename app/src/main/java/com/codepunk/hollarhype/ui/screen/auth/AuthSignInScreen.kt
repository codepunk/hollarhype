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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.common.showErrorSnackBar
import com.codepunk.hollarhype.ui.component.CountryCodePicker
import com.codepunk.hollarhype.ui.component.CountryCodePickerDialog
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.component.PhoneNumber
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonHeight
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.util.http.HttpStatusException

@Composable
fun AuthSignInScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var showRegionPicker by rememberSaveable { mutableStateOf(false) }

    // Do the following when login result is "fresh"
    if (state.isLoginResultFresh) {
        onEvent(AuthEvent.ConsumeLoginResult)
        state.loginResult?.run {
            onLeft { error ->
                if (error.cause !is HttpStatusException) {
                    // HttpStatusExceptions will be handled differently
                    showErrorSnackBar(
                        cause = error.cause,
                        context = LocalContext.current,
                        snackBarHostState = snackBarHostState,
                        coroutineScope = coroutineScope
                    )
                    onEvent(AuthEvent.ClearLoginResult)
                }
            }.onRight { success ->
                if (success) {
                    onEvent(AuthEvent.NavigateToOtp)
                }
            }
        }
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

                val phoneNumberError = state.loginResult?.leftOrNull()
                    ?.errors?.getOrNull(0) ?: ""

                PhoneNumber(
                    regionCode = state.region.regionCode,
                    countryCode = state.region.countryCode,
                    phoneNumber = state.phoneNumber,
                    phoneNumberError = phoneNumberError,
                    onCountryCodeClick = { showRegionPicker = true },
                    onPhoneNumberChange = {
                        onEvent(AuthEvent.ClearLoginResult)
                        onEvent(AuthEvent.UpdatePhoneNumber(it))
                    },
                    onSubmit = {
                        keyboardController?.hide()
                        onEvent(AuthEvent.ClearLoginResult)
                        onEvent(
                            AuthEvent.SignIn(
                                region = state.region,
                                phoneNumber = state.phoneNumber
                            )
                        )
                    }
                )

                TextButton(
                    modifier = Modifier.padding(top = SizeMedium.value),
                    onClick = { onEvent(AuthEvent.RegisterNewPhoneNumber) }
                ) {
                    Text(
                        text = stringResource(id = R.string.phone_number_changed),
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
                    enabled = (!state.isLoading),
                    onClick = {
                        onEvent(AuthEvent.ClearLoginResult)
                        onEvent(
                            AuthEvent.SignIn(
                                region = state.region,
                                phoneNumber = state.phoneNumber
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
                            text = stringResource(id = R.string.sign_in).lowercase(),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }

    if (showRegionPicker) {
        CountryCodePickerDialog(
            onDismiss = { showRegionPicker = false }
        ) {
            CountryCodePicker(
                onItemSelected = {
                    onEvent(AuthEvent.UpdateRegion(it))
                    showRegionPicker = false
                }
            )
        }
    }
}

@ScreenPreviews
@Composable
fun AuthSignInPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthSignInScreen(
                modifier = Modifier.padding(padding),
                state = AuthState()
            )
        }
    }
}
