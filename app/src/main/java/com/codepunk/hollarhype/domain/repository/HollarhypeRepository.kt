package com.codepunk.hollarhype.domain.repository

import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow

interface HollarhypeRepository {

    fun authenticate(): Flow<Ior<DataError, UserSession>>

    fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<DataError, Boolean>>

    fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<DataError, UserSession>>

}
