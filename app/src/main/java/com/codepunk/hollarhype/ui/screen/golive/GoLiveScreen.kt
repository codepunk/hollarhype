package com.codepunk.hollarhype.ui.screen.golive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.ui.component.HHTopAppBar
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun GoLiveScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HHTopAppBar()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Greeting(name = "Go Live")
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
fun GoLiveScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        GoLiveScreen()
    }
}

@Preview
@Composable
fun GoLiveScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        GoLiveScreen()
    }
}