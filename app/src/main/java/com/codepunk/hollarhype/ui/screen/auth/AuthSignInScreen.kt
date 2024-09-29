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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.CountryCodePicker
import com.codepunk.hollarhype.ui.component.CountryCodePickerDialog
import com.codepunk.hollarhype.ui.component.PhoneNumber
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnNavigateToOtp
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnPhoneNumberChange
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnRegionChange
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnRegisterNewPhoneNumber
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnSignIn
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonHeight
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.util.consume
import kotlinx.coroutines.launch

@Composable
fun AuthSignInScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var regionPickerVisible by rememberSaveable { mutableStateOf(false) }

    // Process any consumable (i.e. "single event") values
    state.loginResult?.consume { value ->
        value.onLeft { error ->
            // TODO Is this the best way to determine whether to
            //  show a snackBar? That is, if we got errors that means
            //  we had a valid error result from the backend so the
            //  ViewModel should be able to handle it accordingly
            if (error.errors.isEmpty()) {
                LaunchedEffect(snackBarHostState) {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = error.cause?.message
                                ?: context.getString(R.string.error_unknown)
                        )
                    }
                }
            }
        }.onRight { result ->
            if (result.success) {
                onEvent(OnNavigateToOtp)
            }
        }
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

                PhoneNumber(
                    regionCode = state.region.regionCode,
                    countryCode = state.region.countryCode,
                    phoneNumber = state.phoneNumber,
                    phoneNumberError = state.phoneNumberError,
                    onCountryCodeClick = { regionPickerVisible = true },
                    onPhoneNumberChange = { onEvent(OnPhoneNumberChange(it)) },
                    onSubmit = {
                        onEvent(
                            OnSignIn(
                                region = state.region,
                                phoneNumber = state.phoneNumber
                            )
                        )
                    }
                )

                TextButton(
                    modifier = Modifier.padding(top = SizeMedium.value),
                    onClick = { onEvent(OnRegisterNewPhoneNumber) }
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
                    enabled = (!state.loading),
                    onClick = {
                        onEvent(
                            OnSignIn(
                                region = state.region,
                                phoneNumber = state.phoneNumber
                            )
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
                            text = stringResource(id = R.string.sign_in).lowercase(),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }

    if (regionPickerVisible) {
        CountryCodePickerDialog(
            onDismiss = { regionPickerVisible = false }
        ) {
            CountryCodePicker(
                onItemSelected = {
                    onEvent(OnRegionChange(it))
                    regionPickerVisible = false
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
