package com.codepunk.hollarhype.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.networkBoundResource
import com.codepunk.hollarhype.util.toErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HollarhypeRepositoryImpl(
    private val preferencesDataStore: DataStore<Preferences>,
    private val webservice: HollarhypeWebservice
) : HollarhypeRepository {

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<ErrorResult, Boolean>> = flow {
        webservice.login(
            phoneNumber = phoneNumber,
            regionCode = region.regionCode
        ).mapLeft {
            it.toErrorResult()
        }.map {
            it.success
        }.apply {
            emit(this)
        }
    }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Ior<Throwable, Authentication?>> = networkBoundResource(
        query = {
            TODO()
        },
        fetch = {
            TODO()
        },
        saveFetchResult = {
            TODO()
        }
    )
}
