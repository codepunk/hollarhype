package com.codepunk.hollarhype.domain.repository

import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow

interface HollarhypeRepository {

    fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<ErrorResult, Boolean>>

    fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Ior<Throwable, Authentication?>>

}
