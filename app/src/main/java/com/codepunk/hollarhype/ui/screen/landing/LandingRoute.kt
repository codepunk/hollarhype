package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.ui.screen.landing.LandingScreen

import kotlinx.serialization.Serializable

/**
 * A [LandingRoute] is a "sub-route" of sorts that describes a page in [LandingScreen].
 */
@Serializable
sealed class LandingRoute {

    @Serializable
    data object Activity : LandingRoute()

    @Serializable
    data object GoLive : LandingRoute()

    @Serializable
    data object Hype : LandingRoute()

    @Serializable
    data object Groups : LandingRoute()

}
