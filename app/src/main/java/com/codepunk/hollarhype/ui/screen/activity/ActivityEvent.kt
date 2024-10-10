package com.codepunk.hollarhype.ui.screen.activity

sealed interface ActivityEvent {

    data object Dummy : ActivityEvent

}