package com.codepunk.hollarhype.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private class ContentLoadingIndicatorState(
    private val showDelay: Long,
    private val minShowTime: Long
) {
    private val _isVisible: MutableState<Boolean> = mutableStateOf(false)
    val isVisible: Boolean by _isVisible

    private var startTime: Long = -1L
    private var showJob: Job? = null
    private var hideJob: Job? = null

    fun show(scope: CoroutineScope) {
        cancelDelayedHide()
        showJob = scope.launch {
            delayShow()
        }
    }

    fun hide(scope: CoroutineScope) {
        if (showJob?.isActive == true) {
            // Show didn't happen yet, so just cancel it
            cancelDelayedShow()
        } else {
            // Show did happen, so hide it after some time
            hideJob = scope.launch {
                delayHide()
            }
        }
    }

    private suspend fun delayShow() {
        startTime = -1
        delay(showDelay)
        startTime = System.currentTimeMillis()
        _isVisible.value = true
    }

    private suspend fun delayHide() {
        val diff = System.currentTimeMillis() - startTime
        if (startTime != -1L && diff < minShowTime) {
            // Ensure visible for at least some time
            delay(minShowTime - diff)
        }
        _isVisible.value = false
    }

    private fun cancelDelayedShow() {
        showJob?.cancel()
        showJob = null
        startTime = -1
    }

    private fun cancelDelayedHide() {
        hideJob?.cancel()
        hideJob = null
    }
}

@Composable
fun ContentLoadingIndicator(
    isLoading: Boolean,
    showDelay: Long = 500L,
    minShowTime: Long = 500L,
    content: @Composable (isVisible: Boolean) -> Unit
) {
    val state = remember {
        ContentLoadingIndicatorState(showDelay, minShowTime)
    }
    LaunchedEffect(isLoading) {
        if (isLoading) state.show(this)
        else state.hide(this)
    }
    content(state.isVisible)
}