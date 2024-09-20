package com.codepunk.hollarhype.ui.component

import android.Manifest
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HandlePostNotificationsPermission(
    onShowRationale: () -> Unit = {},
    onGranted: () -> Unit = {},
    onDenied: () -> Unit = {}
) {
    if (Build.VERSION.SDK_INT >= TIRAMISU) {
        val permissionState = rememberPermissionState(
            permission = Manifest.permission.POST_NOTIFICATIONS
        )

        val requestPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                onGranted()
            } else {
                onDenied()
            }
        }

        LaunchedEffect(permissionState) {
            if (!permissionState.status.isGranted && permissionState.status.shouldShowRationale) {
                onShowRationale()
            } else {
                requestPermissionLauncher.launch(permissionState.permission)
            }
        }
    }
}

