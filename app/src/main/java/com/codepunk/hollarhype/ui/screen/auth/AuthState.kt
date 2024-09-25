package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.model.User

data class AuthState(

    // Flags

    val loading: Boolean = false,

    // Data

    val loginResult: Lazy<Either<ErrorResult, Boolean>>? = null,

    val authenticatingUser: User = User(),

    val authenticatedUser: Lazy<Either<ErrorResult, User>>? = null,

    // Error states

    val phoneNumberError: String = ""

)
