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
        startDestination = AuthRoute.AuthInit
    ) {
        composable<AuthRoute.AuthInit> {
            AuthInitScreen(
                state = state
            ) { event ->
                when (event) {
                    is AuthEvent.NavigateToAuthOptions -> {
                        // TODO This seems a little weird that two different
                        //  "things" are processing these events. There should be
                        //  just one.
                        onEvent(event)
                        navController.navigate(AuthRoute.AuthOptions)
                    }
                    else -> onEvent(event)
                }
            }
        }

        composable<AuthRoute.AuthOptions> {
            AuthOptionsScreen(
                state = state,
                onEvent = onEvent
            )
        }

        composable<AuthRoute.AuthSignUp> {
            AuthSignUpScreen(
                state = state,
                onEvent = onEvent
            )
        }

        composable<AuthRoute.AuthSignIn> {
            AuthSignInScreen(
                state = state,
                onEvent = onEvent
            )
        }
    }
}
