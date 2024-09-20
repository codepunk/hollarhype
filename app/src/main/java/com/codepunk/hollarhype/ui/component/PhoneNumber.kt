package com.codepunk.hollarhype.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.theme.LayoutSize
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius

@Composable
fun PhoneNumber(
    modifier: Modifier = Modifier,
    countryCode: String,
    phoneNumber: String,
    onClickCountryCode: () -> Unit,
    onCountryCodeChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(size = buttonCornerRadius),
            onClick = onClickCountryCode
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.width(LayoutSize.LARGE.value),
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
            onValueChange = { onPhoneNumberChange(it) }
        )
    }
}