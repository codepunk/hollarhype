package com.codepunk.hollarhype.data.repository

import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import arrow.core.Either
import arrow.core.Ior
import arrow.core.left
import arrow.core.rightIor
import com.codepunk.hollarhype.data.cachedDataResource
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDataError
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.networkDataResource
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.domain.repository.DataError
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HollarhypeRepositoryImpl(
    private val connectivityManager: ConnectivityManager,
    private val database: HollarhypeDatabase,
    private val dataStore: DataStore<UserSettings>,
    private val webservice: HollarhypeWebservice
) : HollarhypeRepository {

    private val userDao by lazy { database.userDao() }

    override fun authenticate(): Flow<Ior<DataError, UserSession>> = flow {
        val userSettings = dataStore.data.firstOrNull() ?: UserSettings()
        val userSession = userSettings.userSession.toDomain()
        if (userSession is Authenticated) {
            this@flow.emitAll(
                cachedDataResource(
                    query = {
                        dataStore.data.map { it.userSession.toDomain() }
                    },
                    fetch = {
                        try {
                            webservice.authenticate().run {
                                body.mapLeft { it.toDataError(code, message) }
                            }
                        } catch (cause: Throwable) { DataError(cause = cause).left() }
                    },
                    shouldEmitCachedWhileFetching = { false },
                    saveFetchResult = { userDao.insertUser(it.user.toLocal()) }
                )
            )
        } else {
            this@flow.emit(userSession.rightIor())
        }
    }

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<DataError, Boolean>> = networkDataResource(
        fetch = {
            try {
                webservice.login(
                    phoneNumber = phoneNumber,
                    regionCode = region.regionCode
                ).run {
                    body.mapLeft { it.toDataError(code, message) }
                }
            } catch (cause: Throwable) { DataError(cause = cause).left() }
        }
    ) { it.success }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<DataError, UserSession>> = networkDataResource(
        fetch = {
            try {
                webservice.verify(
                    phoneNumber = phoneNumber,
                    otp = otp,
                    regionCode = region.regionCode
                ).run {
                    body.onRight {
                        userDao.insertUser(it.user.toLocal())
                    }.mapLeft {
                        it.toDataError(code, message)
                    }
                }
            } catch (cause: Throwable) { DataError(cause = cause).left() }
        }
    ) {
        it.toDomain()
    }
}
