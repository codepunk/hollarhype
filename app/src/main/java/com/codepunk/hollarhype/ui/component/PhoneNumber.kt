package com.codepunk.hollarhype.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ComponentPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LayoutSize
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

@Composable
fun PhoneNumber(
    modifier: Modifier = Modifier,
    countryCode: Int,
    phoneNumber: String,
    onCountryCodeClick: () -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {}
) {
    // TODO NEXT We seem to be converting from region code to country code and then back again.
    //  Maybe pass more info to this method? Change countryCode to region up the component chain
    val phoneNumberUtil: PhoneNumberUtil by remember { mutableStateOf(PhoneNumberUtil.getInstance()) }
    val region = phoneNumberUtil.getRegionCodeForCountryCode(countryCode)
    Log.d("PhoneNumber", "region=$region")

    val formatted by remember(phoneNumber,region) {
        val parsed = try {
            val pn = phoneNumberUtil.parse(phoneNumber, region)
            phoneNumberUtil.format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
        } catch (e: NumberParseException) {
            phoneNumber
        }
        mutableStateOf(parsed)
    }

    Row(
        modifier = modifier
            .width(IntrinsicSize.Min),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(size = LayoutSize.TINY.value),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            onClick = onCountryCodeClick,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.widthIn(min = LayoutSize.LARGE.value),
                    text = "+$countryCode",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formatted,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = {
                Text(
                    text = stringResource(id = R.string.phone_number),
                    style = MaterialTheme.typography.displaySmall
                )
            },
            onValueChange = { onPhoneNumberChange(it) }
        )
    }
}

@ComponentPreviews
@Composable
fun PreviewPhoneNumber() {
    HollarhypeTheme {
        Surface {
            PhoneNumber(
                countryCode = 1,
                phoneNumber = "7325458674"
            )
        }
    }
}