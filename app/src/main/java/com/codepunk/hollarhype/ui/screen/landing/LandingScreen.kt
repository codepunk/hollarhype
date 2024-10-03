package com.codepunk.hollarhype.ui.screen.landing

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    state: LandingState,
    onEvent: (LandingEvent) -> Unit = {}
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {

                Log.d("LandingScreen", "--------------------------------------------------")
                currentDestination?.hierarchy?.forEach { navDestination ->
                    Log.d("LandingScreen", "navDestination=$navDestination")
                }


                BottomNavItem.entries.forEach { item ->
                    NavigationBarItem(
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
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = stringResource(id = item.contentDescriptionRes)
                            )
                        },
                        label = {
                            Text(text = stringResource(id = item.labelRes))
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        LandingNavigation(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            navController = navController,
            state = state
        )
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
