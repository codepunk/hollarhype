package com.codepunk.hollarhype.ui.screen.auth

import kotlinx.serialization.Serializable

/**
 * An [AuthRoute] is a "sub-route" of sorts that describes a pathway through [AuthScreen].
 */
@Serializable
sealed class AuthRoute {

    @Serializable
    data object AuthInit : AuthRoute()

    @Serializable
    data object AuthOptions : AuthRoute()

    @Serializable
    data object AuthSignUp : AuthRoute()

    @Serializable
    data object AuthSignIn : AuthRoute()

}