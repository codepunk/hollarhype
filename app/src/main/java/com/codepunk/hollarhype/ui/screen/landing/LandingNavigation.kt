package com.codepunk.hollarhype.ui.screen.landing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codepunk.hollarhype.ui.screen.activity.ActivityScreen
import com.codepunk.hollarhype.ui.screen.auth.LandingRoute
import com.codepunk.hollarhype.ui.screen.golive.GoLiveScreen
import com.codepunk.hollarhype.ui.screen.groups.GroupsScreen
import com.codepunk.hollarhype.ui.screen.hype.HypeScreen

@Composable
fun LandingNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    state: LandingState,
    onEvent: (LandingEvent) -> Unit = {}
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LandingRoute.Activity
    ) {
        composable<LandingRoute.Activity> {
            ActivityScreen()
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
