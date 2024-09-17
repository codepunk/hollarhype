package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import arrow.core.left
import arrow.eval.Eval
import com.codepunk.hollarhype.domain.model.User

data class AuthState(
    val authenticatedUser: Eval<Either<Throwable, User>> =
        Eval.now(IllegalStateException("No user").left())
)
