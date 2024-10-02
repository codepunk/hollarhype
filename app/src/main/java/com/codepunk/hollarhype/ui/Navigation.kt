package com.codepunk.hollarhype.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent
import com.codepunk.hollarhype.ui.screen.auth.AuthScreen
import com.codepunk.hollarhype.ui.screen.auth.AuthViewModel
import com.codepunk.hollarhype.ui.screen.landing.LandingScreen
import com.codepunk.hollarhype.ui.screen.landing.LandingViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    startDestination: Route = Route.Auth
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Route.Auth> {
            val viewModel: AuthViewModel = hiltViewModel()
            val state = viewModel.stateFlow.collectAsState()
            AuthScreen(
                modifier = modifier,
                state = state.value
            ) { event ->
                // Consume navigation events here as appropriate,
                // everything else falls through to AuthViewModel
                when (event) {
                    is AuthEvent.NavigateToLanding -> navController.navigate(Route.Landing) {
                        popUpTo(Route.Auth) {
                            inclusive = true
                        }
                    }
                    else -> viewModel.onEvent(event)
                }
            }
        }

        composable<Route.Landing> {
            val viewModel: LandingViewModel = hiltViewModel()
            val state = viewModel.stateFlow.collectAsState()
            LandingScreen(
                modifier = modifier,
                state = state.value
            ) { event ->
                // Consume navigation events here as appropriate,
                // everything else falls through to LandingViewModel
                viewModel.onEvent(event)
            }
        }
    }
}
