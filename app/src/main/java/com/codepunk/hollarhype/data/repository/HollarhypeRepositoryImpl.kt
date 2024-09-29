package com.codepunk.hollarhype.data.repository

import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import arrow.core.Either
import com.codepunk.hollarhype.AppCoroutineScope
import com.codepunk.hollarhype.data.datastore.entity.LocalAuthentication
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.di.qualifier.IoDispatcher
import com.codepunk.hollarhype.domain.model.LoginResult
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.manager.UserSessionManager
import com.codepunk.hollarhype.util.checkConnectivity
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.networkResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HollarhypeRepositoryImpl(
    private val connectivityManager: ConnectivityManager,
    private val database: HollarhypeDatabase,
    private val dataStore: DataStore<UserSettings>,
    private val webservice: HollarhypeWebservice,
    private val userSessionManager: UserSessionManager,
    private val appCoroutineScope: AppCoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HollarhypeRepository {

    private val userDao by lazy { database.userDao() }

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<RepositoryError, LoginResult>> = networkResource(
        doStatusCheck = { connectivityManager.checkConnectivity() },
        makeNetworkRequest = {
            webservice.login(
                phoneNumber = phoneNumber,
                regionCode = region.regionCode
            )
        }
    ) { remote -> remote.toDomain() }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<RepositoryError, Authentication.Authenticated>> = networkResource(
        doStatusCheck = { connectivityManager.checkConnectivity() },
        makeNetworkRequest = {
            webservice.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                regionCode = region.regionCode
            )
        },
        onNetworkRequestFailed = {
            // Clear any currently-authenticated user from dataStore
            appCoroutineScope.launch(ioDispatcher) {
                dataStore.updateData {
                    it.copy(authentication = LocalAuthentication.LocalUnauthenticated)
                }
            }

            // Update userSessionManager
            userSessionManager.logout()
        }
    ) { remote ->
        appCoroutineScope.launch(ioDispatcher) {
            userDao.insertUser(remote.user.toLocal())

            // Set this now-authenticated user into the dataStore
            dataStore.updateData {
                it.copy(authentication = remote.toLocal())
            }
        }
        remote.toDomain()
    }
}
