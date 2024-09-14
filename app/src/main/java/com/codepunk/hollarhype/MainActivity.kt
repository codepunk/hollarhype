package com.codepunk.hollarhype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.codepunk.hollarhype.ui.Navigation
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HollarhypeTheme {
                Navigation()
            }
        }
    }
}
