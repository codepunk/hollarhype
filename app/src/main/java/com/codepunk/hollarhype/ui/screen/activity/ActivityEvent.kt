package com.codepunk.hollarhype.ui.screen.activity

import com.codepunk.hollarhype.domain.model.Activity

interface ActivityEvent {

    data object SelectNone : ActivityEvent

    data class SelectActivity(val activityId: Int) : ActivityEvent

}