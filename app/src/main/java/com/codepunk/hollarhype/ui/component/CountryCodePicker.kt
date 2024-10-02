package com.codepunk.hollarhype.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ComponentPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.SizeLarge
import com.codepunk.hollarhype.ui.theme.SizeSmall
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.intl.getSupportedRegions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodePicker(
    modifier: Modifier = Modifier,
    onItemSelected: (Region) -> Unit = { }
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val supportedRegions by remember { mutableStateOf(getSupportedRegions()) }
    var query by rememberSaveable { mutableStateOf("") }

    val filteredRegions by remember(query) {
        mutableStateOf(
            supportedRegions.filter { region ->
                region.countryName.contains(other = query.trim(), ignoreCase = true)
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(SizeSmall.value)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            text = "${stringResource(id = R.string.select_country)}:"
        )

        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            windowInsets = WindowInsets(top = 0.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = { keyboardController?.hide() },
                    expanded = false,
                    onExpandedChange = { /* No op */ },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (query.isNotBlank()) {
                            IconButton(onClick = { query = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },
            expanded = false,
            onExpandedChange = { /* No op */ },
            content = { /* No op */ }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SizeSmall.value)
        ) {
            items(count = filteredRegions.size) { index ->
                val item = filteredRegions[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SizeLarge.value)
                        .selectable(
                            selected = false,
                            onClick = { onItemSelected(item) }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SizeSmall.value)
                ) {
                    Text(
                        text = item.flagEmoji
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = item.countryName
                    )
                    Text(
                        text = "+${item.countryCode}"
                    )
                }
            }
        }
    }
}

@ComponentPreviews
@Composable
fun CountryCodePickerPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            CountryCodePicker(
                modifier = Modifier.padding(padding)
            )
        }
    }
}
