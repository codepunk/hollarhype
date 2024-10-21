package com.codepunk.hollarhype.ui.screen.activity

import androidx.lifecycle.ViewModel
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: HollarhypeRepository
) : ViewModel() {

    // region Variables

    // We use StateFlow here instead of State/mutableStateOf to keep Compose-related
    // constructs out of ViewModel
    private val _stateFlow: MutableStateFlow<ActivityState> = MutableStateFlow(ActivityState())
    val stateFlow = _stateFlow.asStateFlow()

    private var state: ActivityState
        get() = _stateFlow.value
        set(value) { _stateFlow.value = value }

    // endregion Variables

    // region Methods

    fun setActivityId(activityId: Long?) {
        state = state.copy(
            activityId = activityId ?: -1L
        )
    }

    fun clearActivity() {
        state = state.copy(
            activityId = -1L,
            activity = null
        )
    }

    fun onEvent(event: ActivityEvent) {

    }

    // endregion Methods

}