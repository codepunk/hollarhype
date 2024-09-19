package com.codepunk.hollarhype.ui.screen.auth

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.foundation.layout.widthIn
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
import com.codepunk.hollarhype.ui.theme.currentWindowAdaptiveInfoCustom
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.Size
import com.codepunk.hollarhype.ui.theme.largeGutterSize
import com.codepunk.hollarhype.ui.theme.standardButtonWidth

@Composable
fun AuthSignUpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val layoutMargin = layoutMargin().times(2)

    Log.d("Size", "MIN=${Size.MIN}")
    Log.d("Size", "TINY=${Size.TINY}")
    Log.d("Size", "SMALL=${Size.SMALL}")
    Log.d("Size", "MEDIUM=${Size.MEDIUM}")
    Log.d("Size", "LARGE=${Size.LARGE}")
    Log.d("Size", "X_LARGE=${Size.X_LARGE}")
    Log.d("Size", "XX_LARGE=${Size.XX_LARGE}")
    Log.d("Size", "XXX_LARGE=${Size.XXX_LARGE}")
    Log.d("Size", "HUGE=${Size.HUGE}")

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = layoutMargin,
                end = layoutMargin
            ),
            contentAlignment = Alignment.Center
    ) {
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AuthSignUpLandscape(onEvent = onEvent)
        } else {
            AuthSignUpPortrait(onEvent = onEvent)
        }
    }
}

@Composable
fun AuthSignUpPortrait(
    modifier: Modifier = Modifier,
    onEvent: (AuthEvent) -> Unit
) {
    val sizeClass = currentWindowAdaptiveInfoCustom().windowSizeClass.windowWidthSizeClass
    val avatarSize = if (sizeClass == WindowWidthSizeClass.COMPACT) {
        Size.X_LARGE.mid
    } else {
        Size.XX_LARGE.mid
    }

    Column(
        modifier = Modifier
            .widthIn(max = Size.XXX_LARGE.mid)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = Size.LARGE.value,
            alignment = Alignment.CenterVertically
        )
    ) {
        UserAvatar(
            modifier = Modifier.width(avatarSize),
            onEvent = onEvent
        )

        SignUpForm(
            modifier = Modifier.fillMaxWidth()
        )

        SignUpSubmit(
            modifier = Modifier.fillMaxWidth(),
            onEvent = onEvent
        )
    }
}

@Composable
fun AuthSignUpLandscape(
    modifier: Modifier = Modifier,
    onEvent: (AuthEvent) -> Unit
) {
    val sizeClass = currentWindowAdaptiveInfoCustom().windowSizeClass.windowHeightSizeClass
    val avatarSize = if (sizeClass == WindowHeightSizeClass.COMPACT) {
        Size.X_LARGE.mid
    } else {
        Size.XX_LARGE.mid
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
                    .widthIn(max = Size.HUGE.value)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = Size.LARGE.value,
                    alignment = Alignment.CenterVertically
                )
            ) {
                UserAvatar(
                    modifier = Modifier.width(avatarSize),
                    onEvent = onEvent
                )

                SignUpSubmit(
                    modifier = Modifier.fillMaxWidth(),
                    onEvent = onEvent
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
                    .widthIn(max = Size.HUGE.value)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    onEvent: (AuthEvent) -> Unit
) {
    Box(
        modifier = modifier.aspectRatio(1f)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            painter = painterResource(R.drawable.img_default_user_96),
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
    var countryCode by remember { mutableStateOf("+1") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Size.SMALL.value)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
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
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
            label = {
                Text(
                    text = stringResource(id = R.string.last_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailAddress,
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
            label = {
                Text(
                    text = stringResource(id = R.string.email_address),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { /* No op */ }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Size.SMALL.value)
        ) {
            OutlinedButton(
                shape = RoundedCornerShape(size = buttonCornerRadius),
                onClick = { /* No op */ }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(Size.LARGE.value),
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
                modifier = Modifier.fillMaxWidth(),
                value = phoneNumber,
                colors = OutlinedTextFieldDefaults.colors().copy(
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                maxLines = 1,
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
        verticalArrangement = Arrangement.spacedBy(Size.MEDIUM.value)
    ) {
        Text(
            text = stringResource(id = R.string.disclaimer),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier.width(standardButtonWidth),
            shape = RoundedCornerShape(size = buttonCornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            onClick = { onEvent(AuthEvent.NavigateToSignIn) }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up).lowercase(),
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
