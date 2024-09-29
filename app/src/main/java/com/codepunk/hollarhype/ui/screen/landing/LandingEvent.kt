package com.codepunk.hollarhype.ui.screen.landing

import com.codepunk.hollarhype.ui.screen.auth.AuthEvent

sealed interface LandingEvent {

    // Update data

    data class UpdateEmailAddress(val value: String) : LandingEvent

}
