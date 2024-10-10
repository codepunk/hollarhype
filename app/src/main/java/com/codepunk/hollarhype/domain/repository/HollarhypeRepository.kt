package com.codepunk.hollarhype.domain.repository

import androidx.paging.PagingData
import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.domain.model.Activity
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

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

    fun activityFeed(
        deviceDateTime: LocalDateTime,
        page: Int
    ): Flow<PagingData<Activity>>

}
