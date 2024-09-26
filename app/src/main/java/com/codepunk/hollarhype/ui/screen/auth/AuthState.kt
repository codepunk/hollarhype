package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.util.intl.Region

data class AuthState(

    // Flags

    val loading: Boolean = false,

    // Data

    val firstName: String = "",
    val lastName: String = "",
    val emailAddress: String = "",
    val region: Region = Region.getDefault(),
    val phoneNumber: String = "",
    val otp: String = "",

    // Events/results

    val loginResult: Lazy<Either<ErrorResult, Boolean>>? = null,

    val authenticatedUser: Lazy<Either<ErrorResult, User>>? = null,

    // Error states

    val phoneNumberError: String = ""

)
