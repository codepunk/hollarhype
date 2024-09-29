package com.codepunk.hollarhype.data.repository

import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import arrow.core.Either
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.LoginResult
import com.codepunk.hollarhype.domain.model.VerifyResult
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.util.checkConnectivity
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.networkResource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class HollarhypeRepositoryImpl(
    @ApplicationContext private val connectivityManager: ConnectivityManager,
    private val dataStore: DataStore<UserSettings>,
    private val database: HollarhypeDatabase,
    private val webservice: HollarhypeWebservice
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

    fun login2(
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
    ): Flow<Either<RepositoryError, VerifyResult>> = networkResource(
        doStatusCheck = { connectivityManager.checkConnectivity() },
        makeNetworkRequest = {
            webservice.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                regionCode = region.regionCode
            )
        }
    ) { remote ->
        remote.toDomain()
    }.onEach {
        // TODO Save user and authToken, also save into UserComponent
    }
}
