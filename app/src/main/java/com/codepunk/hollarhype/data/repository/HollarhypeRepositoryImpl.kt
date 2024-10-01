package com.codepunk.hollarhype.data.repository

import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import arrow.core.Either
import arrow.core.Ior
import com.codepunk.hollarhype.data.cachedResource
import com.codepunk.hollarhype.data.datastore.entity.LocalAuthentication
import com.codepunk.hollarhype.data.datastore.entity.LocalAuthentication.LocalUnauthenticated
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDataError
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.networkResource
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthenticatedUserResult
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.di.qualifier.ApplicationScope
import com.codepunk.hollarhype.di.qualifier.IoDispatcher
import com.codepunk.hollarhype.domain.model.Authentication.Authenticated
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.domain.repository.DataError
import com.codepunk.hollarhype.manager.UserSessionManager
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HollarhypeRepositoryImpl(
    private val connectivityManager: ConnectivityManager,
    private val database: HollarhypeDatabase,
    private val dataStore: DataStore<UserSettings>,
    private val webservice: HollarhypeWebservice,
    private val userSessionManager: UserSessionManager,
    @ApplicationScope private val appCoroutineScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HollarhypeRepository {

    private val userDao by lazy { database.userDao() }

    override fun authenticate(): Flow<Ior<DataError, User>> = cachedResource(
        query = {
            // Get user using userSettings & local db
            val userSettings = dataStore.data.firstOrNull()
            when (val authenticated = userSettings?.authentication) {
                is LocalAuthentication.LocalAuthenticated -> {
                    // TODO Do I need to do something here with authentication userId & authToken .. ?
                    userDao.getUser(authenticated.userId).map { it?.toDomain() }
                }
                else -> flowOf(null)
            }
        },
        fetch = {
            webservice.getAuthenticatedUser().run {
                body.mapLeft { it.toDataError(code, message) }
            }
        },
        onFetchCaught = { DataError(cause = it) },
        saveFetchResult = { userDao.insertUser(it.user.toLocal()) },
        onNoData = {
            // This "blank" DataError will be ignored
            DataError()
        },
    )

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<DataError, Boolean>> = networkResource(
        fetch = {
            webservice.login(
                phoneNumber = phoneNumber,
                regionCode = region.regionCode
            ).run {
                body.mapLeft { it.toDataError(code, message) }
            }
        },
        onFetchCaught = { DataError(cause = it) }
    ) { it.success }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<DataError, Authenticated>> = networkResource(
        fetch = {
            webservice.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                regionCode = region.regionCode
            ).run {
                body.mapLeft { it.toDataError(code, message) }
            }
        },
        onFetchCaught = { DataError(cause = it) },
        onFetchFailed = { onVerifyFail() }
    ) {
        onVerifySuccess(it)
        it.toDomain()
    }

    private fun onVerifySuccess(authentication: RemoteAuthentication) {
        appCoroutineScope.launch(ioDispatcher) {
            userDao.insertUser(authentication.user.toLocal())
            dataStore.updateData {
                it.copy(authentication = authentication.toLocal())
            }
        }
    }

    private fun onVerifyFail() {
        appCoroutineScope.launch(ioDispatcher) {
            dataStore.updateData {
                it.copy(authentication = LocalUnauthenticated)
            }
        }
    }
}
