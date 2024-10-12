package com.codepunk.hollarhype.ui.screen.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LocalSizes

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    state: ActivityState,
    onEvent: (ActivityEvent) -> Unit = {}
) {
    val activityFeed = state.activityFeedFlow.collectAsLazyPagingItems()
    val activityFeedLazyListState = rememberLazyListState()

    Scaffold(
        modifier = modifier,
        topBar = {
            HollarHypeTopAppBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    start = LocalSizes.current.paddingLarge,
                    top = LocalSizes.current.paddingLarge,
                    end = LocalSizes.current.paddingLarge,
                )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(id = R.string.activity_feed).uppercase()
            )
            Spacer(modifier = Modifier.height(LocalSizes.current.padding))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(LocalSizes.current.paddingLarge),
                state = activityFeedLazyListState
            ) {
                items(
                    count = activityFeed.itemCount,
                    key = activityFeed.itemKey { it.id }
                ) { index ->
                    activityFeed[index]?.also {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(LocalSizes.current.regionSmall),
                            onClick = { /*TODO*/ }
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(LocalSizes.current.padding),
                                text = AnnotatedString.fromHtml(it.activityText)
                            )
                        }
                    }
                }
            }
        }
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
