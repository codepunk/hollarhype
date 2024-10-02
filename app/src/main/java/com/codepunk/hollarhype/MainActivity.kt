package com.codepunk.hollarhype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.manager.UserSessionManager
import com.codepunk.hollarhype.ui.Navigation
import com.codepunk.hollarhype.ui.Route
import com.codepunk.hollarhype.ui.screen.splash.SplashViewModel
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userSessionManager: UserSessionManager

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { splashViewModel.isLoading.value }

        enableEdgeToEdge()
        setContent {
            val userSession = userSessionManager.userSession.collectAsState()

            HollarhypeTheme {
                Navigation(
                    startDestination = if (userSession.value is Authenticated) {
                        Route.Landing
                    } else {
                        Route.Auth
                    }
                )
            }
        }
    }
}
