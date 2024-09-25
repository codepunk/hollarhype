package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import arrow.core.right
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.model.User

data class AuthState(

    // Flags

    val loading: Boolean = false,

    val loginResultUnread: Boolean = false,

    // Data

    val loginResult: Either<ErrorResult, Boolean> = false.right(),

    val authenticatingUser: User = User(),

    val authenticatedUser: Either<ErrorResult, User?> = null.right(),

    // Error states

    val phoneNumberError: String = ""

)
