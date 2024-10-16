package com.codepunk.hollarhype.ui.screen.group

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GroupViewModel @Inject constructor() : ViewModel() {

    // region Variables

    // We use StateFlow here instead of State/mutableStateOf to keep Compose-related
    // constructs out of ViewModel
    private val _stateFlow: MutableStateFlow<GroupState> = MutableStateFlow(GroupState())
    val stateFlow = _stateFlow.asStateFlow()

    private var state: GroupState
        get() = _stateFlow.value
        set(value) { _stateFlow.value = value }

    // endregion Variables

    // region Methods

    fun onEvent(event: GroupEvent) {

    }

    // endregion Methods

}