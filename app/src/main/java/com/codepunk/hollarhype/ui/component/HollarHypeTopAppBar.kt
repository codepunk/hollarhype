package com.codepunk.hollarhype.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HollarHypeTopAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    onNavigateUp: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.padding_tiny)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_bar_icon),
                    contentDescription = stringResource(id = R.string.content_hollarhype)
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onNavigateUp
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.content_back)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun HHTopAppBarPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        HollarHypeTopAppBar()
    }
}

@Preview
@Composable
fun HHTopAppBarPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        HollarHypeTopAppBar()
    }
}
