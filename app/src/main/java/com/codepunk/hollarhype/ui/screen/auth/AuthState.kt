package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.domain.model.User

data class AuthState(

    // Flags

    val loading: Boolean = false,

    val loginResultUnread: Boolean = false,

    // Data

    val loginResult: Either<Throwable, Boolean> = false.right(),

    val authenticatingUser: User = User(),

    val authenticatedUser: Eval<Either<Throwable, User>> =
        Eval.now(IllegalStateException("No user").left())

)
