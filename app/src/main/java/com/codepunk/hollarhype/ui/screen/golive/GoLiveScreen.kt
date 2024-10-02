package com.codepunk.hollarhype.ui.screen.golive

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
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.activity.ActivityScreen
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun GoLiveScreen(
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
            Text(text = "Go Live")
        }
    }
}

@ScreenPreviews
@Composable
fun GoLivePreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            GoLiveScreen(
                modifier = Modifier.padding(padding)
            )
        }
    }
}
