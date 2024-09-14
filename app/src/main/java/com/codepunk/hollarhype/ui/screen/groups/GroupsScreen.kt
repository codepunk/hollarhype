package com.codepunk.hollarhype.ui.screen.groups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codepunk.hollarhype.ui.component.HHTopAppBar
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun GroupsScreen(
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
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Greeting(name = "Groups")
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
fun GroupsScreenPreviewDark() {
    HollarhypeTheme(darkTheme = true) {
        GroupsScreen()
    }
}

@Preview
@Composable
fun GroupsScreenPreviewLight() {
    HollarhypeTheme(darkTheme = false) {
        GroupsScreen()
    }
}