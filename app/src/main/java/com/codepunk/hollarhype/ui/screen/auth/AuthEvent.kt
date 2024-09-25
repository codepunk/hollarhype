package com.codepunk.hollarhype.ui.screen.auth

import com.codepunk.hollarhype.util.intl.Region

sealed interface AuthEvent {

    // Data changes

    sealed class DataChange<T>(open val value: T): AuthEvent {
        data class OnEmailAddressChange(override val value: String): DataChange<String>(value)
        data class OnFirstNameChange(override val value: String): DataChange<String>(value)
        data class OnLastNameChange(override val value: String): DataChange<String>(value)
        data class OnPhoneNumberChange(override val value: String): DataChange<String>(value)
        data class OnRegionChange(override val value: Region): DataChange<Region>(value)
    }

    // Navigation

    sealed interface NavigationEvent: AuthEvent {
        data object OnNavigateToSignIn : NavigationEvent
        data object OnNavigateToSignUp : NavigationEvent
        data object OnNavigateToOtp : NavigationEvent
    }

    // User actions

    data object OnEditAvatar: AuthEvent
    data object OnRegisterNewPhoneNumber: AuthEvent
    data object OnSignUp: AuthEvent
    data object OnSignIn: AuthEvent
    data object OnResendOtp: AuthEvent

}
