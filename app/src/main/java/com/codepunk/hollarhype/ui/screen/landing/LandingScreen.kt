package com.codepunk.hollarhype.ui.screen.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codepunk.hollarhype.ui.component.HollarHypeTopAppBar
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import kotlinx.coroutines.flow.map

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
                BottomNavItem.entries.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(item::class)
                        } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
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
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LandingNavigation(
                navController = navController,
                state = state
            )
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
