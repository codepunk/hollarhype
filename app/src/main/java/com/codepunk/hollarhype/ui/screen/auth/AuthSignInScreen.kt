package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.CountryCodePicker
import com.codepunk.hollarhype.ui.component.CountryCodePickerDialog
import com.codepunk.hollarhype.ui.component.PhoneNumber
import com.codepunk.hollarhype.ui.component.Region
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonWidth

@Composable
fun AuthSignInScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var region by rememberSaveable { mutableStateOf(Region.getDefault()) }

    var showPicker by rememberSaveable { mutableStateOf(false) }

    val layoutMargin = layoutMargin().times(2)
    Box(
        modifier = modifier
            .fillMaxSize()
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
                regionCode = region.regionCode,
                countryCode = region.countryCode,
                phoneNumber = phoneNumber,
                onCountryCodeClick = { showPicker = true },
                onPhoneNumberChange = { phoneNumber = it }
            )

            TextButton(
                modifier = Modifier.padding(top = SizeMedium.value),
                onClick = { onEvent(AuthEvent.OnPhoneNumberChanged) }
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
                modifier = Modifier.width(standardButtonWidth),
                shape = RoundedCornerShape(size = buttonCornerRadius),
                onClick = { onEvent(
                    AuthEvent.OnSignIn(
                        countryCode = region.countryCode,
                        phoneNumber = phoneNumber
                    )
                ) }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in).lowercase(),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

    }

    if (showPicker) {
        CountryCodePickerDialog(
            onDismiss = { showPicker = false }
        ) {
            CountryCodePicker(
                onItemSelected = {
                    region = it
                    showPicker = false
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