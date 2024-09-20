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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LayoutSize
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.currentWindowAdaptiveInfoCustom
import com.codepunk.hollarhype.ui.theme.largeGutterSize
import com.codepunk.hollarhype.ui.theme.layoutMargin
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlin.math.sqrt

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AuthSignUpScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val formValues = remember { mutableStateOf(FormValues()) }

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
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AuthSignUpLandscape(
                formValues = formValues,
                onEditAvatar = { onEvent(AuthEvent.EditAvatar) },
                onSubmit = {
                    submitForm() // TODO Will need some sort of return value
                }
            )
        } else {
            AuthSignUpPortrait(
                formValues = formValues,
                onEditAvatar = { onEvent(AuthEvent.EditAvatar) },
                onSubmit = {
                    submitForm() // TODO Will need some sort of return value
                }
            )
        }
    }
}

@Composable
fun AuthSignUpPortrait(
    modifier: Modifier = Modifier,
    formValues: MutableState<FormValues>,
    onEditAvatar: () -> Unit,
    onSubmit: () -> Unit
) {
    val sizeClass = currentWindowAdaptiveInfoCustom().windowSizeClass.windowWidthSizeClass
    val avatarSize = if (sizeClass == WindowWidthSizeClass.COMPACT) {
        LayoutSize.X_LARGE.mid
    } else {
        LayoutSize.XX_LARGE.mid
    }

    Column(
        modifier = Modifier
            .widthIn(max = LayoutSize.XXX_LARGE.mid)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = LayoutSize.LARGE.value,
            alignment = Alignment.CenterVertically
        )
    ) {
        UserAvatar(
            modifier = Modifier.width(avatarSize),
            onClick = onEditAvatar
        )

        SignUpForm(
            modifier = Modifier.fillMaxWidth(),
            formValues = formValues
        )

        SignUpSubmit(
            modifier = Modifier.fillMaxWidth(),
            onSubmit = onSubmit
        )
    }
}

@Composable
fun AuthSignUpLandscape(
    modifier: Modifier = Modifier,
    formValues: MutableState<FormValues>,
    onEditAvatar: () -> Unit,
    onSubmit: () -> Unit
) {
    val sizeClass = currentWindowAdaptiveInfoCustom().windowSizeClass.windowHeightSizeClass
    val avatarSize = if (sizeClass == WindowHeightSizeClass.COMPACT) {
        LayoutSize.X_LARGE.mid
    } else {
        LayoutSize.XX_LARGE.mid
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
                    .widthIn(max = LayoutSize.HUGE.value)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = LayoutSize.LARGE.value,
                    alignment = Alignment.CenterVertically
                )
            ) {
                UserAvatar(
                    modifier = Modifier.width(avatarSize),
                    onClick = onEditAvatar
                )

                SignUpSubmit(
                    modifier = Modifier.fillMaxWidth(),
                    onSubmit = onSubmit
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
                    .widthIn(max = LayoutSize.HUGE.value)
                    .fillMaxWidth(),
                formValues = formValues
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
        var imageSize: Float by remember { mutableFloatStateOf(0f) }
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clickable(onClick = onClick)
                .onSizeChanged {
                    imageSize = it.width.toFloat()
                },
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
                        containerColor = MaterialTheme.colorScheme.secondary
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
    formValues: MutableState<FormValues>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
            value = formValues.value.firstName,
            label = {
                Text(
                    text = stringResource(id = R.string.first_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { formValues.value = formValues.value.copy(firstName = it) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formValues.value.lastName,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
            label = {
                Text(
                    text = stringResource(id = R.string.last_name),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { formValues.value = formValues.value.copy(lastName = it) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formValues.value.emailAddress,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
            label = {
                Text(
                    text = stringResource(id = R.string.email_address),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { formValues.value = formValues.value.copy(emailAddress = it) }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
        ) {
            OutlinedButton(
                shape = RoundedCornerShape(size = buttonCornerRadius),
                onClick = { /* No op */ }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(LayoutSize.LARGE.value),
                        text = formValues.value.countryCode,
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
                value = formValues.value.phoneNumber,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                maxLines = 1,
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                onValueChange = { formValues.value = formValues.value.copy(phoneNumber = it) }
            )
        }
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
        verticalArrangement = Arrangement.spacedBy(LayoutSize.MEDIUM.mid)
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
            onClick = { onSubmit() }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up).lowercase(),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}

data class FormValues(
    val firstName: String = "",
    val lastName: String = "",
    val emailAddress: String = "",
    val countryCode: String = "+1",
    val phoneNumber: String = ""
)

private fun submitForm() {

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
