package com.codepunk.hollarhype.ui.screen.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codepunk.hollarhype.di.qualifier.IoDispatcher
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: HollarhypeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
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

    // region Constructors

    init {
        activityFeed(
            deviceDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            page = 1
        )
    }

    // endregion Constructors

    // region Methods

    private fun activityFeed(
        deviceDateTime: LocalDateTime,
        page: Int
    ) {
        viewModelScope.launch(ioDispatcher) {
            repository.activityFeed(
                deviceDateTime = deviceDateTime,
                page = page
            ).collect {
                val x = "$it"
            }
        }
    }

    fun onEvent(event: ActivityEvent) {

    }

    // endregion Methods
}