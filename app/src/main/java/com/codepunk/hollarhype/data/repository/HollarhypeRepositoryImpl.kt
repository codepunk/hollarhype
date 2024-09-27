package com.codepunk.hollarhype.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import arrow.core.Either
import arrow.core.Ior
import arrow.core.left
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.model.LoginResult
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HollarhypeRepositoryImpl(
    private val preferencesDataStore: DataStore<Preferences>,
    private val webservice: HollarhypeWebservice
) : HollarhypeRepository {

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<RepositoryError, LoginResult>> = flow {
        emit(
            try {
                webservice.login(
                    phoneNumber = phoneNumber,
                    regionCode = region.regionCode
                ).body
                    .mapLeft { it.toDomain() }
                    .map { it.toDomain() }
            } catch (cause: Throwable) {
                RepositoryError(cause = cause).left()
            }
        )
    }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Ior<Throwable, Authentication?>> = flow {
        TODO("Not yet implemented")
    }

    /*
    override fun verify2(
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
                ifLeft = { _ -> throw RuntimeException() }, // TODO Change this
                ifRight = { auth -> auth }
            ).apply {
                Log.d("HollarhypeRepositoryImpl", "verify: fetch=$this")
            }
        },
        saveFetchResult = {
            userWithAuthTokenDao.insertUserWithAuthToken(it.toLocal())
        }
    )
     */
}
