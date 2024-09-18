package com.codepunk.hollarhype.ui.screen.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.largeGutterSize
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.theme.layoutMarginWidth
import com.codepunk.hollarhype.ui.theme.mediumSize
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.ui.theme.xLargePadding
import com.codepunk.hollarhype.ui.theme.xLargeSize
import com.codepunk.hollarhype.ui.theme.xxLargePadding
import com.codepunk.hollarhype.ui.theme.xxLargeSize
import com.codepunk.hollarhype.ui.theme.xxxLargeSize

@Composable
fun AuthSignUpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val layoutMargin = remember {
        layoutMarginWidth(windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass)
    }

    val orientation = LocalConfiguration.current.orientation

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = layoutMargin,
                end = layoutMargin
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val avatarSize = when (windowAdaptiveInfo.windowSizeClass.windowHeightSizeClass) {
                WindowHeightSizeClass.MEDIUM -> xxLargeSize
                WindowHeightSizeClass.EXPANDED -> xxxLargeSize
                else -> xLargeSize
            }

            val fillMaxWidthFraction = when (windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.MEDIUM -> 0.8f
                WindowWidthSizeClass.EXPANDED -> 0.8f
                else -> 0.8f
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(fillMaxWidthFraction)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .padding(start = xLargePadding, end = xLargePadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = xLargePadding,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    UserAvatar(
                        modifier = Modifier
                            .width(avatarSize),
                        onEvent = onEvent
                    )

                    SignUpSubmit(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onEvent = onEvent
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(largeGutterSize)
                        .fillMaxHeight()
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SignUpForm(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        } else {
            val avatarSize = when (windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.MEDIUM -> xxLargeSize
                WindowWidthSizeClass.EXPANDED -> xxxLargeSize
                else -> xLargeSize
            }

            val fillMaxWidthFraction = when (windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.MEDIUM -> 0.8f
                WindowWidthSizeClass.EXPANDED -> 0.6f
                else -> 0.8f
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(fillMaxWidthFraction)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = xxLargePadding,
                    alignment = Alignment.CenterVertically
                )
            ) {
                UserAvatar(
                    modifier = Modifier
                        .width(avatarSize),
                    onEvent = onEvent
                )

                SignUpForm(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                SignUpSubmit(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    onEvent: (AuthEvent) -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            painter = painterResource(R.drawable.img_default_user),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("+33") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(largePadding)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            value = firstName,
            label = {
                Text(
                    text = stringResource(id = R.string.first_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = lastName,
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            label = {
                Text(
                    text = stringResource(id = R.string.last_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = emailAddress,
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            label = {
                Text(
                    text = stringResource(id = R.string.email_address),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(largePadding)
        ) {
            OutlinedButton(
                shape = RoundedCornerShape(size = buttonCornerRadius),
                onClick = { /*TODO*/ }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(mediumSize),
                        text = countryCode,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = null
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = phoneNumber,
                colors = OutlinedTextFieldDefaults.colors().copy(
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                onValueChange = { /* No op */ }
            )
        }
    }
}

@Composable
fun SignUpSubmit(
    modifier: Modifier = Modifier,
    onEvent: (AuthEvent) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(largePadding)
    ) {
        Text(
            text = stringResource(id = R.string.disclaimer),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .width(standardButtonWidth),
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
