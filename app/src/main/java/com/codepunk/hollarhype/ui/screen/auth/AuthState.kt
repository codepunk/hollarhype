package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import com.codepunk.hollarhype.domain.model.LoginResult
import com.codepunk.hollarhype.domain.repository.RepositoryError
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

    val loginResult: Lazy<Either<RepositoryError, LoginResult>>? = null,

    val authenticatedUser: Lazy<Either<RepositoryError, User>>? = null,

    // Error states

    val phoneNumberError: String = ""

)
