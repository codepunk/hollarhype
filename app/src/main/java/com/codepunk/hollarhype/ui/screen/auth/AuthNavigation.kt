package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.padding(paddingValues),
        navController = navController,
        startDestination = AuthRoute.AuthOptions
    ) {
        composable<AuthRoute.AuthOptions> {
            AuthStartScreen { event ->
                // Consume navigation events here as appropriate,
                // everything else gets passed up the chain
                when (event) {
                    is AuthEvent.NavigateToSignUp ->
                        navController.navigate(AuthRoute.AuthSignUp)
                    is AuthEvent.NavigateToSignIn ->
                        navController.navigate(AuthRoute.AuthSignIn)
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthSignUp> {
            AuthSignUpScreen(
                state = state
            ) { event ->
                // Consume navigation events here as appropriate,
                // everything else gets passed up the chain
                when (event) {
                    is AuthEvent.NavigateUp -> navController.navigateUp()
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthSignIn> {
            AuthSignInScreen(
                state = state
            ) { event ->
                // Consume navigation events here as appropriate,
                // everything else gets passed up the chain
                when (event) {
                    is AuthEvent.NavigateUp -> navController.navigateUp()
                    is AuthEvent.NavigateToOtp -> navController.navigate(AuthRoute.AuthOtp)
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthOtp> {
            AuthOtpScreen(
                state = state
            ) { event ->
                // Consume navigation events here as appropriate,
                // everything else gets passed up the chain
                when (event) {
                    is AuthEvent.NavigateUp -> navController.navigateUp()
                    else -> onEvent(event)
                }
            }
        }
    }
}
