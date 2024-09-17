package com.codepunk.hollarhype.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codepunk.hollarhype.ui.screen.auth.AuthScreen
import com.codepunk.hollarhype.ui.screen.auth.AuthViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Auth
    ) {
        composable<Route.Auth> {
            val viewModel: AuthViewModel = hiltViewModel()
            val state = viewModel.stateFlow.collectAsState()
            AuthScreen(
                modifier = modifier,
                state = state.value
            ) { event ->
                // So the thing is, when we want to actually navigate
                // AWAY from auth and into Landing, how do we do that?
                // I guess we also need an AuthEvent.NavigateToLanding
                viewModel.onEvent(event)
            }
        }
    }
}
