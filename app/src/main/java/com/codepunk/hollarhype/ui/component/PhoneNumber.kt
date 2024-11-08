package com.codepunk.hollarhype.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ComponentPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LocalSizes
import com.codepunk.hollarhype.ui.theme.roundedCorner
import com.codepunk.hollarhype.util.intl.PhoneNumberVisualTransformation
import com.codepunk.hollarhype.util.intl.Region
import com.google.i18n.phonenumbers.PhoneNumberUtil

@Composable
fun PhoneNumber(
    modifier: Modifier = Modifier,
    regionCode: String,
    countryCode: Int,
    phoneNumber: String = "",
    phoneNumberError: String = "",
    onCountryCodeClick: () -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    val sizes = LocalSizes.current

    val visualTransformation by remember(regionCode) {
        mutableStateOf(
            PhoneNumberVisualTransformation(regionCode)
        )
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = phoneNumber,
        leadingIcon = {
            TextButton(
                shape = RoundedCornerShape(size = roundedCorner),
                onClick = onCountryCodeClick
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.widthIn(min = sizes.componentSmall),
                        text = "+$countryCode",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = null
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        textStyle = MaterialTheme.typography.labelMedium,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions { onSubmit() },
        label = {
            Text(
                text = stringResource(id = R.string.phone_number),
            )
        },
        supportingText = {
            if (phoneNumberError.isNotBlank()) {
                Text(
                    maxLines = 1,
                    text = phoneNumberError,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        visualTransformation = visualTransformation,
        onValueChange = { onPhoneNumberChange(it) }
    )
}

@ComponentPreviews
@Composable
fun PreviewUSPhoneNumber() {
    HollarhypeTheme {
        Surface {
            val region = Region.of("US")
            PhoneNumber(
                regionCode = region.regionCode,
                countryCode = region.countryCode,
                phoneNumber = PhoneNumberUtil.getInstance().run {
                    getNationalSignificantNumber(
                        getExampleNumber(region.regionCode)
                    )
                },
                phoneNumberError = "Invalid phone number"
            )
        }
    }
}

@ComponentPreviews
@Composable
fun PreviewFRPhoneNumber() {
    HollarhypeTheme {
        Surface {
            val region = Region.of("FR")
            PhoneNumber(
                regionCode = region.regionCode,
                countryCode = region.countryCode,
                phoneNumber = PhoneNumberUtil.getInstance().run {
                    getNddPrefixForRegion(region.regionCode, true) +
                            getNationalSignificantNumber(
                                getExampleNumber(region.regionCode)
                            )
                }
            )
        }
    }
}