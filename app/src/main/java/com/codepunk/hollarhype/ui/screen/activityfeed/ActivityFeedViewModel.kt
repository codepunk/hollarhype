package com.codepunk.hollarhype.ui.screen.activityfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
class ActivityFeedViewModel @Inject constructor(
    private val repository: HollarhypeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    // region Variables

    // We use StateFlow here instead of State/mutableStateOf to keep Compose-related
    // constructs out of ViewModel
    private val _stateFlow: MutableStateFlow<ActivityFeedState> = MutableStateFlow(ActivityFeedState())
    val stateFlow = _stateFlow.asStateFlow()

    private var state: ActivityFeedState
        get() = _stateFlow.value
        set(value) { _stateFlow.value = value }

    // endregion Variables

    // region Constructors

    init {
        loadActivityFeed()
    }

    // endregion Constructors

    // region Methods

    // Load data

    private fun loadActivityFeed(
        deviceDateTime: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    ) {
        viewModelScope.launch(ioDispatcher) {
            repository.loadActivityFeed(
                deviceDateTime = deviceDateTime
            ).cachedIn(viewModelScope).apply {
                state = state.copy(
                    activityFeedFlow = this,
                    isActivityFeedFresh = true
                )
            }
        }
    }

    // Events/results

    private fun consumeActivityFeed() {
        state = state.copy(
            isActivityFeedFresh = false
        )
    }

    // Event delegate

    fun onEvent(event: ActivityFeedEvent) {
        when (event) {
            ActivityFeedEvent.Load -> loadActivityFeed()
            ActivityFeedEvent.ConsumeActivityFeed -> consumeActivityFeed()
        }
    }

    // endregion Methods
}