package com.codepunk.hollarhype.ui.screen.run

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RunViewModel @Inject constructor() : ViewModel() {

    // region Variables

    // We use StateFlow here instead of State/mutableStateOf to keep Compose-related
    // constructs out of ViewModel
    private val _stateFlow: MutableStateFlow<RunState> = MutableStateFlow(RunState())
    val stateFlow = _stateFlow.asStateFlow()

    private var state: RunState
        get() = _stateFlow.value
        set(value) { _stateFlow.value = value }

    // endregion Variables

    // region Methods

    fun onEvent(event: RunEvent) {

    }

    // endregion Methods

}