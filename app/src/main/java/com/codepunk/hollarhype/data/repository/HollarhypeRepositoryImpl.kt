package com.codepunk.hollarhype.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.data.local.dao.UserDao
import com.codepunk.hollarhype.data.local.dao.UserWithAuthTokenDao
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.networkBoundResource
import com.codepunk.hollarhype.util.toErrorResult
import com.codepunk.hollarhype.util.toRepositoryException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HollarhypeRepositoryImpl(
    private val preferencesDataStore: DataStore<Preferences>,
    private val userWithAuthTokenDao: UserWithAuthTokenDao,
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
            userWithAuthTokenDao.getUserWithAuthTokenByPhoneNumber(phoneNumber).map {
                Log.d("HollarhypeRepositoryImpl", "verify: it=$it")
                it.toDomain().apply {
                    Log.d("HollarhypeRepositoryImpl", "verify: query=$this")
                }
            }
        },
        fetch = {
            webservice.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                regionCode = region.regionCode
            ).fold(
                ifLeft = { callError -> throw callError.toErrorResult().toRepositoryException() }, // TODO Simplify this
                ifRight = { auth -> auth }
            ).apply {
                Log.d("HollarhypeRepositoryImpl", "verify: fetch=$this")
            }
        },
        saveFetchResult = {
            userWithAuthTokenDao.insertUserWithAuthToken(it.toLocal())
        }
    )
}
