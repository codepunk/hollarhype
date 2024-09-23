package com.codepunk.hollarhype.domain.repository

import arrow.core.Either
import com.codepunk.hollarhype.ui.component.Region
import kotlinx.coroutines.flow.Flow

interface HollarhypeRepository {

    fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<Throwable, Boolean>>

}