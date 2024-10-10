package com.codepunk.hollarhype.ui.screen.auth

import arrow.core.Either
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.domain.repository.RepositoryException
import com.codepunk.hollarhype.util.intl.Region

data class AuthState(

    // Flags

    val isLoading: Boolean = false,

    // Data

    val firstName: String = "",
    val lastName: String = "",
    val emailAddress: String = "",
    val region: Region = Region.getDefault(),
    val phoneNumber: String = "",
    val otp: String = "",

    // Events/results

    val isLoginResultFresh: Boolean = false,
    val loginResult: Either<RepositoryException, Boolean>? = null,

    val isVerifyResultFresh: Boolean = false,
    val verifyResult: Either<RepositoryException, UserSession>? = null

)
