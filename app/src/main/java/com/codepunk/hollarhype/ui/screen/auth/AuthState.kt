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

    val authenticateResult: Lazy<Ior<DataError, User>>? = null,
    val loginResult: Lazy<Either<DataError, Boolean>>? = null,
    val verifyResult: Lazy<Either<DataError, Authentication.Authenticated>>? = null,

    // Error states

    val phoneNumberError: String = ""

)
