package com.codepunk.hollarhype.ui.screen.activity

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.Activity
import com.codepunk.hollarhype.domain.util.getAvatarUrl
import com.codepunk.hollarhype.domain.util.getContentDescriptionResId
import com.codepunk.hollarhype.ui.common.showErrorSnackBar
import com.codepunk.hollarhype.ui.component.ContentLoadingIndicator
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.LocalSizes
import com.codepunk.hollarhype.util.http.HttpStatusException
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    state: ActivityState,
    onEvent: (ActivityEvent) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val activityFeed = state.activityFeedFlow.collectAsLazyPagingItems()
    val loadState = activityFeed.loadState
    val activityFeedLazyListState = rememberLazyListState()

    // Handle refreshing, loading
    val isRefreshing: Boolean by remember(loadState) {
        mutableStateOf(
            loadState.refresh is LoadState.Loading
        )
    }

    val isLoading: Boolean by remember(loadState) {
        mutableStateOf(
            loadState.refresh is LoadState.Loading ||
            loadState.append is LoadState.Loading
        )
    }

    // Handle error
    val error by remember(loadState) {
        mutableStateOf(
            when {
                loadState.refresh is LoadState.Error ->
                    (loadState.refresh as LoadState.Error).error
                loadState.append is LoadState.Error ->
                    (loadState.append as LoadState.Error).error
                else -> null
            }
        )
    }

    error?.run {
        if (this !is HttpStatusException) {
            showErrorSnackBar(
                cause = error,
                context = LocalContext.current,
                snackBarHostState = snackBarHostState,
                coroutineScope = coroutineScope
            )
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HollarHypeTopAppBar()
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LocalSizes.current.paddingLarge)
            ) {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = stringResource(id = R.string.activity_feed).uppercase()
                )
                ContentLoadingIndicator(isLoading = isLoading) { isVisible ->
                    if (isVisible) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(LocalSizes.current.component2xSmall),
                            strokeWidth = LocalSizes.current.border
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(LocalSizes.current.padding))
            PullToRefreshBox(
                modifier = Modifier.fillMaxSize(),
                isRefreshing = isRefreshing,
                onRefresh = { onEvent(ActivityEvent.Load) }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(LocalSizes.current.paddingLarge),
                    state = activityFeedLazyListState,
                    contentPadding = PaddingValues(bottom = LocalSizes.current.paddingLarge)
                ) {
                    items(
                        count = activityFeed.itemCount,
                        key = activityFeed.itemKey { it.id }
                    ) { index ->
                        activityFeed[index]?.also { activity ->
                            ActivityCard(activity = activity)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActivityCard(
    modifier: Modifier = Modifier,
    activity: Activity
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSizes.current.paddingLarge)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LocalSizes.current.padding)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(LocalSizes.current.componentLarge)
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(activity.getAvatarUrl())
                        .build(),
                    placeholder = painterResource(id = R.drawable.img_default_avatar_opaque_96),
                    error = painterResource(id = R.drawable.img_default_avatar_opaque_96),
                    contentDescription = stringResource(
                        activity.data.getContentDescriptionResId()
                    ),
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSizes.current.padding),
                    text = AnnotatedString.fromHtml(activity.activityText),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.End),
                text = "To contain timestamp",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@ScreenPreviews
@Composable
fun ActivityPreviews() {
    val activities = listOf(
        Activity(
            id = 1,
            activityText = "<b>User</b> ended a live session",
            createdAt = Clock.System.now().minus(5.days)
        ),
        Activity(
            id = 2,
            activityText = "<b>User</b> went live",
            createdAt = Clock.System.now().minus(8.days)
        ),
        Activity(
            id = 3,
            activityText = "<b>User</b> ended a live session",
            createdAt = Clock.System.now().minus(10.days)
        ),
        Activity(
            id = 4,
            activityText = "<b>User A</b> messaged <b>User B</b>",
            createdAt = Clock.System.now().minus(12.days)
        ),
        Activity(
            id = 5,
            activityText = "<b>User</b> went live",
            createdAt = Clock.System.now().minus(18.days)
        )
    )
    HollarhypeTheme {
        Scaffold { padding ->
            ActivityScreen(
                modifier = Modifier.padding(padding),
                state = ActivityState(
                    activityFeedFlow = flowOf(
                        PagingData.from(activities)
                    )
                )
            )
        }
    }
}
