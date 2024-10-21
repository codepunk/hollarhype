package com.codepunk.hollarhype.ui.screen.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LocalAppColors

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    state: ActivityState,
    onEvent: (ActivityEvent) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            color = LocalAppColors.current.onBackground,
            text = "${stringResource(id = R.string.activity)} ${state.activityId}"
        )
    }
}

@ScreenPreviews
@Composable
fun ActivityPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            ActivityScreen(
                modifier = Modifier.padding(padding),
                state = ActivityState()
            )
        }
    }
}
