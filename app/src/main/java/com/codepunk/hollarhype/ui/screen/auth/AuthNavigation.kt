package com.codepunk.hollarhype.ui.screen.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnNavigateToOtp
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnNavigateToSignIn
import com.codepunk.hollarhype.ui.screen.auth.AuthEvent.OnNavigateToSignUp

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
            AuthStartScreen(
                state = state
            ) { event ->
                // Pass navigation events up to navController, everything else
                // will fall through to AuthViewModel
                when (event) {
                    is OnNavigateToSignUp ->
                        navController.navigate(AuthRoute.AuthSignUp)
                    is OnNavigateToSignIn ->
                        navController.navigate(AuthRoute.AuthSignIn)
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthSignUp> {
            AuthSignUpScreen(
                state = state
            ) { event ->
                // Pass navigation events up to navController, everything else
                // will fall through to AuthViewModel
                onEvent(event)
            }
        }

        composable<AuthRoute.AuthSignIn> {
            AuthSignInScreen(
                state = state
            ) { event ->
                // Pass navigation events up to navController, everything else
                // will fall through to AuthViewModel
                when (event) {
                    is OnNavigateToOtp -> navController.navigate(AuthRoute.AuthOtp)
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthOtp> {
            AuthOtpScreen(
                state = state
            ) { event ->
                // Pass navigation events up to navController, everything else
                // will fall through to AuthViewModel
                // Pass navigation events up to navController, everything else
                // will fall through to AuthViewModel
                onEvent(event)
            }
        }
    }
}
