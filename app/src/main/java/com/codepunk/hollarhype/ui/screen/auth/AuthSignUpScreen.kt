package com.codepunk.hollarhype.ui.screen.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.CountryCodePicker
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.component.PhoneNumber
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.SignUp
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.Size2xLarge
import com.codepunk.hollarhype.ui.theme.Size3xLarge
import com.codepunk.hollarhype.ui.theme.SizeHuge
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeMedium
import com.codepunk.hollarhype.ui.theme.SizeSmall
import com.codepunk.hollarhype.ui.theme.SizeXLarge
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.currentWindowAdaptiveInfoCustom
import com.codepunk.hollarhype.ui.theme.largeGutterSize
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonHeight
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.util.intl.Region
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthSignUpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var showRegionPicker by rememberSaveable { mutableStateOf(false) }
    val modalBottomSheetState: SheetState = rememberModalBottomSheetState()

    // Do the following when signup result is "fresh"
    // ...

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
            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                SignUpLandscape(
                    state = state,
                    onEvent = onEvent,
                    onShowRegionPicker = { showRegionPicker = true }
                )
            } else {
                SignUpNonLandscape(
                    state = state,
                    onEvent = onEvent,
                    onShowRegionPicker = { showRegionPicker = true }
                )
            }
        }
    }

    if (showRegionPicker) {
        ModalBottomSheet(
            onDismissRequest = { showRegionPicker = false },
            sheetState = modalBottomSheetState
        ) {
            CountryCodePicker(
                modifier = Modifier.padding(SizeMedium.mid),
                onItemSelected = {
                    onEvent(AuthEvent.UpdateRegion(it))
                    showRegionPicker = false
                }
            )
        }
    }
}

@Composable
fun SignUpNonLandscape(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit,
    onShowRegionPicker: () -> Unit
) {

    // Avatar size is based on width
    val windowSizeClass = currentWindowAdaptiveInfoCustom().windowSizeClass.windowWidthSizeClass
    val avatarSize = when (windowSizeClass) {
        WindowWidthSizeClass.COMPACT -> SizeXLarge.mid
        else -> Size2xLarge.mid
    }

    Column(
        modifier = Modifier
            .widthIn(max = Size3xLarge.mid)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = SizeLarge.value,
            alignment = Alignment.CenterVertically
        )
    ) {
        UserAvatar(
            modifier = Modifier.width(avatarSize),
            onClick = { onEvent(AuthEvent.EditAvatar) }
        )

        SignUpForm(
            modifier = Modifier.fillMaxWidth(),
            firstName = state.firstName,
            lastName = state.lastName,
            emailAddress = state.emailAddress,
            phoneNumber = state.phoneNumber,
            region = state.region,
            onFirstNameChange = { onEvent(AuthEvent.UpdateFirstName(it)) },
            onLastNameChange = { onEvent(AuthEvent.UpdateLastName(it)) },
            onEmailAddressChange = { onEvent(AuthEvent.UpdateEmailAddress(it)) },
            onPhoneNumberChange = { onEvent(AuthEvent.UpdatePhoneNumber(it)) },
            onShowRegionPicker = onShowRegionPicker
        )

        SignUpSubmit(
            modifier = Modifier.fillMaxWidth(),
            onSubmit = {
                onEvent(
                    SignUp(
                        firstName = state.firstName,
                        lastName = state.lastName,
                        emailAddress = state.emailAddress,
                        region = state.region,
                        phoneNumber = state.phoneNumber
                    )
                )
            }
        )
    }
}

@Composable
fun SignUpLandscape(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit,
    onShowRegionPicker: () -> Unit
) {
    // Avatar size is based on height
    val windowSizeClass = currentWindowAdaptiveInfoCustom().windowSizeClass.windowHeightSizeClass
    val avatarSize = when (windowSizeClass) {
        WindowHeightSizeClass.COMPACT -> SizeXLarge.mid
        else -> Size2xLarge.mid
    }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = SizeHuge.value)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = SizeLarge.value,
                    alignment = Alignment.CenterVertically
                )
            ) {
                UserAvatar(
                    modifier = Modifier.width(avatarSize),
                    onClick = { onEvent(AuthEvent.EditAvatar) }
                )

                SignUpSubmit(
                    modifier = Modifier.fillMaxWidth(),
                    onSubmit = {
                        onEvent(
                            SignUp(
                                firstName = state.firstName,
                                lastName = state.lastName,
                                emailAddress = state.emailAddress,
                                region = state.region,
                                phoneNumber = state.phoneNumber
                            )
                        )
                    }
                )
            }
        }

        Spacer(
            modifier = Modifier
                .width(largeGutterSize)
                .fillMaxHeight()
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            SignUpForm(
                modifier = Modifier
                    .widthIn(max = SizeHuge.value)
                    .fillMaxWidth(),
                firstName = state.firstName,
                lastName = state.lastName,
                emailAddress = state.emailAddress,
                phoneNumber = state.phoneNumber,
                region = state.region,
                onFirstNameChange = { onEvent(AuthEvent.UpdateFirstName(it)) },
                onLastNameChange = { onEvent(AuthEvent.UpdateLastName(it)) },
                onEmailAddressChange = { onEvent(AuthEvent.UpdateEmailAddress(it)) },
                onPhoneNumberChange = { onEvent(AuthEvent.UpdatePhoneNumber(it)) },
                onShowRegionPicker = onShowRegionPicker
            )
        }
    }
}

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.aspectRatio(1f)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clickable(onClick = onClick),
            painter = painterResource(R.drawable.img_default_user_96),
            contentDescription = stringResource(id = R.string.app_name)
        )

        // Determine the size of a camera button whose center will rest
        // on the edge of the avatar circle
        val sqrt2 = sqrt(2f)
        val iconFraction = 1f - ((sqrt2 - 1f) / sqrt2)

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(iconFraction)
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(iconFraction)
                )

                FilledIconButton(
                    modifier = Modifier.fillMaxSize(),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = onClick
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(1 / sqrt2),
                        painter = painterResource(id = R.drawable.ic_camera_black_24),
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    emailAddress: String,
    phoneNumber: String,
    region: Region,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailAddressChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onShowRegionPicker: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SizeSmall.value)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            maxLines = 1,
            singleLine = true,
            value = firstName,
            label = {
                Text(
                    text = stringResource(id = R.string.first_name)
                )
            },
            onValueChange = onFirstNameChange
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            maxLines = 1,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.last_name),
                )
            },
            onValueChange = onLastNameChange
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailAddress,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            maxLines = 1,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.email_address),
                )
            },
            onValueChange = onEmailAddressChange
        )

        PhoneNumber(
            modifier = modifier.fillMaxWidth(),
            regionCode = region.regionCode,
            countryCode = region.countryCode,
            phoneNumber = phoneNumber,
            onCountryCodeClick = { onShowRegionPicker() },
            onPhoneNumberChange = onPhoneNumberChange
        )
    }
}

@Composable
fun SignUpSubmit(
    modifier: Modifier = Modifier,
    onSubmit: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SizeMedium.mid)
    ) {
        Text(
            text = stringResource(id = R.string.disclaimer),
          style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .width(standardButtonWidth)
                .height(standardButtonHeight),
            shape = RoundedCornerShape(size = buttonCornerRadius),
            onClick = { onSubmit() }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up).lowercase(),
                style = MaterialTheme.typography.labelLarge
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
