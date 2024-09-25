package com.codepunk.hollarhype.domain.repository

import arrow.core.Either
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow

interface HollarhypeRepository {

    fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<ErrorResult, Boolean>>

}