package com.codepunk.hollarhype.ui.screen.landing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.screen.activityfeed.ActivityFeedListDetailScreen
import com.codepunk.hollarhype.ui.screen.activityfeed.ActivityFeedScreen
import com.codepunk.hollarhype.ui.screen.activityfeed.ActivityFeedViewModel
import com.codepunk.hollarhype.ui.screen.golive.GoLiveScreen
import com.codepunk.hollarhype.ui.screen.groups.GroupsScreen
import com.codepunk.hollarhype.ui.screen.hype.HypeScreen
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.util.currentWindowAdaptiveInfoCustom

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    state: LandingState,
    onEvent: (LandingEvent) -> Unit = {}
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val adaptiveInfo = currentWindowAdaptiveInfoCustom()
    val customNavSuiteType = with(adaptiveInfo) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.EXPANDED -> when (windowSizeClass.windowHeightSizeClass) {
                WindowHeightSizeClass.COMPACT -> NavigationSuiteType.NavigationRail
                else -> NavigationSuiteType.NavigationDrawer
            }
            else -> NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }

    NavigationSuiteScaffold(
        modifier = modifier,
        layoutType = customNavSuiteType,
        contentColor = Color.White,
        navigationSuiteItems = {
            LandingNavItem.entries.forEach { item ->
                item(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = stringResource(id = item.contentDescriptionRes)
                        )
                    },
                    label = { Text(text = stringResource(id = item.labelRes)) },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == item.route::class.qualifiedName
                    } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = LandingRoute.Activity
        ) {
            composable<LandingRoute.Activity> {
                /*
                val viewModel: ActivityFeedViewModel = hiltViewModel()
                val activityFeedState = viewModel.stateFlow.collectAsState()
                ActivityFeedScreen(
                    modifier = modifier,
                    state = activityFeedState.value
                ) { event ->
                    viewModel.onEvent(event)
                }
                 */

                ActivityFeedListDetailScreen(
                    modifier = modifier
                )
            }

            composable<LandingRoute.GoLive> {
                GoLiveScreen()
            }

            composable<LandingRoute.Hype> {
                HypeScreen()
            }

            composable<LandingRoute.Groups> {
                GroupsScreen()
            }
        }
    }
}

@ScreenPreviews
@Composable
fun LandingPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            LandingScreen(
                modifier = Modifier.padding(padding),
                state = LandingState()
            )
        }
    }
}
