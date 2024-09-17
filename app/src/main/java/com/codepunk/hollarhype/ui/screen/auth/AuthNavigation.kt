package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    stateFlow: StateFlow<AuthState>,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.padding(paddingValues),
        navController = navController,
        startDestination = AuthRoute.AuthInit
    ) {
        composable<AuthRoute.AuthInit> {
            AuthInitScreen(
                stateFlow = stateFlow
            ) { event ->
                // Pass navigation events up to navController, everything else
                // will fall through to AuthViewModel
                when (event) {
                    is AuthEvent.NavigateToAuthOptions ->
                        navController.navigate(AuthRoute.AuthOptions)
                    is AuthEvent.NavigateToSignUp ->
                        navController.navigate(AuthRoute.AuthSignUp)
                    is AuthEvent.NavigateToSignIn ->
                        navController.navigate(AuthRoute.AuthSignIn)
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthOptions> {
            AuthOptionsScreen(
                stateFlow = stateFlow,
                onEvent = onEvent
            )
        }

        composable<AuthRoute.AuthSignUp> {
            AuthSignUpScreen(
                stateFlow = stateFlow,
                onEvent = onEvent
            )
        }

        composable<AuthRoute.AuthSignIn> {
            AuthSignInScreen(
                stateFlow = stateFlow,
                onEvent = onEvent
            )
        }
    }
}
