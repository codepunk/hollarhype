package com.codepunk.hollarhype.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

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
                    flagEmoji = "",
                    phoneFormat = ""
                )
            }.sortedBy { countryInfo ->
                countryInfo.region.takeUnless { it == currentRegion } ?: ""
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(LayoutSize.MEDIUM.value),
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
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }
                )
            },
            expanded = expanded,
            onExpandedChange = {}
        ) {

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = countries.size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(LayoutSize.LARGE.value)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = countries[index].countryName
                    )
                    Text(
                        text = "+${countries[index].countryCode}"
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
