package com.codepunk.hollarhype.data.repository

import androidx.datastore.core.DataStore
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.mapper.toRepositoryError
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.LoginResult
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.domain.model.VerifyResult
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HollarhypeRepositoryImpl(
    private val dataStore: DataStore<UserSettings>,
    private val database: HollarhypeDatabase,
    private val webservice: HollarhypeWebservice
) : HollarhypeRepository {

    private val userDao by lazy { database.userDao() }

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<RepositoryError, LoginResult>> = flow {
        emit(
            try {
                webservice.login(
                    phoneNumber = phoneNumber,
                    regionCode = region.regionCode
                ).apply {
                    // Handle HTTP result, headers, etc. here if needed
                }.body
                    .mapLeft { it.toRepositoryError() }
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
    ): Flow<Either<RepositoryError, VerifyResult>> = flow {
        emit(
            try {
                val response = webservice.verify(
                    phoneNumber = phoneNumber,
                    otp = otp,
                    regionCode = region.regionCode
                ).apply {
                    // Handle HTTP result, headers, etc. here if needed
                }.body.mapLeft { it.toRepositoryError() }

                // We now have response = Either<RepositoryError, RemoteVerifyResult>

                // We need to save locally to database (user) and dataStore (authToken)
                response.map { result ->
                    userDao.insertUser(result.user.toLocal())
                    dataStore.updateData {
                        it.copy(authentication = result.toLocal())
                    }

                    val userSettingsFlow = dataStore.data
                    val userFlow = userDao.getUser(result.user.id)
                    val combined = userSettingsFlow.combine(userFlow) { userSettings, user ->
                        VerifyResult(
                            user = user?.toDomain() ?: User(),
                            authToken = userSettings.authentication.authToken
                        )
                    }

                    // combined = Flow<VerifyResult>
                    // Need to return Flow<Either<RepositoryError, VerifyResult>>
                    val firstOrNull = combined.firstOrNull() ?: VerifyResult()
                    firstOrNull
                }
            } catch (cause: Throwable) {
                RepositoryError(cause = cause).left()
            }
        )
    }
}
