package com.codepunk.hollarhype.ui.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.ui.preview.ComponentPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.sizes
import com.codepunk.hollarhype.util.intl.Region
import kotlin.math.roundToInt

private const val BLINKING_CURSOR = "blinking_cursor"

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    otpLength: Int = 6,
    onTextChange: (String, Boolean) -> Unit = { _, _ -> },
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    LaunchedEffect(Unit) {
        if (text.length > otpLength) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpLength characters")
        }
    }

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(text, selection = TextRange(text.length)),
        onValueChange = {
            if (it.text.length <= otpLength) {
                onTextChange(it.text, it.text.length == otpLength)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Go
        ),
        keyboardActions = keyboardActions,
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(sizes.padding)
            ) {
                repeat(otpLength) { index ->
                    CharField(
                        index = index,
                        text = text
                    )
                }
            }
        }
    )
}

@Composable
fun CharField(
    modifier: Modifier = Modifier,
    text: String = "",
    index: Int
) {
    val blinkTransition = rememberInfiniteTransition(BLINKING_CURSOR)
    var focused by remember { mutableStateOf(true) }
    val current by remember(text, index) { mutableStateOf(text.length == index) }
    val char = when {
        index > text.length -> ""
        index < text.length -> text[index].toString()
        focused -> "_"
        else -> ""
    }

    Text(
        modifier = Modifier
            .width(sizes.componentMedium)
            .border(
                width = sizes.border,
                color = if (current) {
                    OutlinedTextFieldDefaults.colors().focusedIndicatorColor
                } else {
                    OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor
                },
                shape = OutlinedTextFieldDefaults.shape
            )
            .padding(sizes.paddingLarge)
            .background(
                color = if (current) {
                    OutlinedTextFieldDefaults.colors().focusedContainerColor
                } else {
                    OutlinedTextFieldDefaults.colors().unfocusedContainerColor
                }
            )
            .onFocusChanged { focusState ->
                //focused = focusState.isFocused
            },
        text = char,
        minLines = 1,
        maxLines = 1,
        style = MaterialTheme.typography.headlineLarge,
        color = when {
            !focused -> OutlinedTextFieldDefaults.colors().unfocusedTextColor
            !current -> OutlinedTextFieldDefaults.colors().unfocusedTextColor
            else -> blinkTransition.animateColor(
                initialValue = Color.Transparent,
                targetValue = OutlinedTextFieldDefaults.colors().focusedTextColor,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 500,
                        easing = { it.roundToInt().toFloat() }),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = BLINKING_CURSOR
            ).value
        },
        textAlign = TextAlign.Center
    )
}

@ComponentPreviews
@Composable
fun PreviewOtpTextField() {
    HollarhypeTheme {
        Surface {
            val region = Region.of("FR")
            OtpTextField(
                text = "123",
                otpLength = 5
            )
        }
    }
}