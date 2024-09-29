package com.codepunk.hollarhype.ui

import kotlinx.serialization.Serializable

/**
 * A [Route] describes a pathway through the Hollarhype app.
 */
@Serializable
sealed class Route {

    @Serializable
    data object Auth: Route()

    @Serializable
    data object Landing: Route()

    @Serializable
    data object Activity: Route()

    @Serializable
    data object GoLive: Route()

    @Serializable
    data object Hype: Route()

    @Serializable
    data object Groups: Route()

}