package com.codepunk.hollarhype.ui.common

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.util.http.NoConnectivityException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun showErrorSnackBar(
    cause: Throwable?,
    context: Context,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    message: () -> String = {
        when (cause) {
            is NoConnectivityException -> context.getString(R.string.error_no_internet_try_again)
            else -> cause?.localizedMessage ?: context.getString(R.string.error_unknown)
        }
    }
): Boolean {
    LaunchedEffect(snackBarHostState) {
        coroutineScope.launch(Dispatchers.Main) {
            snackBarHostState.showSnackbar(
                message = message()
            )
        }
    }
    return true
}