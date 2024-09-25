package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import arrow.eval.Eval
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.model.User

data class AuthState(

    // Flags

    val loading: Boolean = false,

    // Data

    val loginResult: Eval<Either<ErrorResult, Boolean>>? = null,

    val authenticatingUser: User = User(),

    val authenticatedUser: Eval<Either<ErrorResult, User>>? = null,

    // Error states

    val phoneNumberError: String = ""

)
