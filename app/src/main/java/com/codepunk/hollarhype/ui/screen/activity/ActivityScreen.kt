package com.codepunk.hollarhype.ui.screen.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HollarHypeTopAppBar()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Greeting(name = "Activity")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
fun ActivityScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        ActivityScreen()
    }
}

@Preview
@Composable
fun ActivityScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        ActivityScreen()
    }
}