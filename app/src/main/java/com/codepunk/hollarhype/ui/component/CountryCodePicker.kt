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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LayoutSize
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.Locale as JavaLocale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodePicker(
    modifier: Modifier = Modifier,
    onItemSelected: (CountryInfo) -> Unit = {}
) {
    var query by rememberSaveable { mutableStateOf("") }

    val phoneNumberUtil: PhoneNumberUtil by remember { mutableStateOf(PhoneNumberUtil.getInstance()) }
    val countries by remember {
        val currentRegion = Locale.current.region
        mutableStateOf(
            phoneNumberUtil.supportedRegions.map { region ->
                val countryCode = phoneNumberUtil.getCountryCodeForRegion(region)
                CountryInfo(
                    region = region,
                    countryName = JavaLocale("", region).displayCountry,
                    countryCode = countryCode,
                    flagEmoji = getFlagEmoji(region),
                    phoneFormat = ""
                )
            }.sortedBy { countryInfo ->
                countryInfo.region.takeUnless { it == currentRegion } ?: ""
            }
        )
    }

    val filteredList: List<CountryInfo> by rememberSaveable(query) {
        mutableStateOf(
            countries.filter { countryInfo ->
                countryInfo.countryName.contains(
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
                    onExpandedChange = { },
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
            onExpandedChange = {}
        ) {

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(LayoutSize.SMALL.value)
        ) {
            items(count = filteredList.size) { index ->
                val item = filteredList[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LayoutSize.LARGE.value)
                        .selectable(
                            selected = false,
                            enabled = true,
                            role = null,
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

data class CountryInfo(
    val region: String = "",
    val countryName: String = "",
    val countryCode: Int = 1,
    val flagEmoji: String = "",
    val phoneFormat: String = ""
)

fun getFlagEmoji(region: String): String {
    if (region.length != 2) return ""
    val regionUpper = region.uppercase()
    if (!regionUpper[0].isLetter() || !regionUpper[1].isLetter()) return ""
    val firstLetter = Character.codePointAt(regionUpper, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(regionUpper, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

@ScreenPreviews
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
