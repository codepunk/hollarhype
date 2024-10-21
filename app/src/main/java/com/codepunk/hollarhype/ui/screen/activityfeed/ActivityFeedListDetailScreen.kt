package com.codepunk.hollarhype.ui.screen.activityfeed

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.activity.ActivityScreen
import com.codepunk.hollarhype.ui.screen.activity.ActivityViewModel
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ActivityFeedListDetailScreen(
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val coroutineScope = rememberCoroutineScope()

    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            val viewModel: ActivityFeedViewModel = hiltViewModel()
            val activityFeedState = viewModel.stateFlow.collectAsState()
            ActivityFeedScreen(
                modifier = Modifier,
                state = activityFeedState.value
            ) { event ->
                when (event) {
                    is ActivityFeedEvent.SelectActivity -> {
                        coroutineScope.launch {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = event.activity.id
                            )

                            // TODO Is there a way to NOT have navigation added to
                            // back stack when in 2-panel mode?
                        }
                    }
                    else -> viewModel.onEvent(event)
                }
            }
        },
        detailPane = {
            val viewModel: ActivityViewModel = hiltViewModel()
            val activityState = viewModel.stateFlow.collectAsState()
            val selectedActivityId = navigator.currentDestination?.contentKey as? Long
            selectedActivityId?.run {
                viewModel.setActivityId(this)
            } ?: viewModel.clearActivity()
            AnimatedPane {
                ActivityScreen(
                    modifier = Modifier,
                    state = activityState.value
                ) { event ->
                    viewModel.onEvent(event)
                }
            }
        }
    )
}


@ScreenPreviews
@Composable
fun ActivityFeedListDetailPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            ActivityFeedListDetailScreen(
                modifier = Modifier.padding(padding)
            )
        }
    }
}
