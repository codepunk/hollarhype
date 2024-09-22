package com.codepunk.hollarhype.ui.component

import android.R
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.baseline

@Composable
fun CountryCodePickerDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    style = baseline.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = stringResource(id = R.string.cancel)
                )
            }
        },
        confirmButton = { },
        text = { content() }
    )
}

@ScreenPreviews
@Composable
fun CountryCodePickerDialogPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            CountryCodePickerDialog(
                modifier = Modifier.padding(padding)
            ) {
                CountryCodePicker()
            }
        }
    }
}
