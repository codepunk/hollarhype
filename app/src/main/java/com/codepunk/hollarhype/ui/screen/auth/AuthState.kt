package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.repository.DataError
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

    val authResultFresh: Boolean = false,
    val authResult: Ior<DataError, User>? = null,

    val loginResultFresh: Boolean = false,
    val loginResult: Either<DataError, Boolean>? = null,

    val verifyResultFresh: Boolean = false,
    val verifyResult: Either<DataError, Authentication.Authenticated>? = null,

)
