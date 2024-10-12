package com.codepunk.hollarhype.ui.screen.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LocalSizes
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    state: ActivityState,
    onEvent: (ActivityEvent) -> Unit = {}
) {
    val activityFeed = remember(state.activityFeed) {
        flow { emit(state.activityFeed) }
    }.collectAsLazyPagingItems()

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
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSizes.current.paddingLarge),
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(id = R.string.activity_feed).uppercase()
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = LocalSizes.current.paddingLarge,
                        end = LocalSizes.current.paddingLarge
                    ),
                verticalArrangement = Arrangement.spacedBy(LocalSizes.current.padding),
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
