package com.codepunk.hollarhype.domain.repository

import androidx.paging.PagingData
import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.domain.model.Activity
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface HollarhypeRepository {

    fun authenticate(): Flow<Ior<RepositoryException, UserSession>>

    fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<RepositoryException, Boolean>>

    fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<RepositoryException, UserSession>>

    fun loadActivityFeed(
        deviceDateTime: LocalDateTime
    ): Flow<PagingData<Activity>>

    fun getActivity(activityId: Long): Flow<Ior<RepositoryException, Activity?>>

}
