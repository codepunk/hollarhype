package com.codepunk.hollarhype.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ComponentPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LayoutSize
import com.codepunk.hollarhype.util.PhoneUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodePicker(
    modifier: Modifier = Modifier,
    onItemSelected: (PhoneUtil.Region) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        // Initialize PhoneUtil eagerly to avoid lag the first time we use it
        PhoneUtil.initialize()
    }

    var query by rememberSaveable { mutableStateOf("") }

    val regions: List<PhoneUtil.Region> by rememberSaveable(query) {
        mutableStateOf(
            PhoneUtil.supportedRegions.filter { region ->
                region.countryName.contains(
                    other = query,
                    ignoreCase = true
                )
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            text = "${stringResource(id = R.string.select_country)}:"
        )

        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = { Log.d("CountryCodePicker", "onSearch") },
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
            onExpandedChange = { /* No op */ }
        ) {

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
        ) {
            items(count = regions.size) { index ->
                val item = regions[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LayoutSize.LARGE.value)
                        .selectable(
                            selected = false,
                            onClick = { onItemSelected(item) }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
                ) {
                    Text(
                        text = item.flagEmoji
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f),
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
