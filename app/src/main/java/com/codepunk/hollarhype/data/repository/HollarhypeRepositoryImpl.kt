package com.codepunk.hollarhype.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.LoginResult
import com.codepunk.hollarhype.domain.model.VerifyResult
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.domain.repository.RepositoryError
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.toRepositoryError
import com.hadiyarajesh.flower_core.Resource
import com.hadiyarajesh.flower_core.Resource.Status
import com.hadiyarajesh.flower_core.networkResource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class HollarhypeRepositoryImpl(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<UserSettings>,
    private val database: HollarhypeDatabase,
    private val webservice: HollarhypeWebservice
) : HollarhypeRepository {

    private val userDao by lazy { database.userDao() }

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<RepositoryError, LoginResult>> = networkResource(
        makeNetworkRequest = {
            webservice.login(
                phoneNumber = phoneNumber,
                regionCode = region.regionCode
            )
        }
    ).mapToNetworkFlow { it.toDomain() }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<RepositoryError, VerifyResult>> = networkResource(
        makeNetworkRequest = {
            webservice.verify(
                phoneNumber = phoneNumber,
                otp = otp,
                regionCode = region.regionCode
            )
        }
    ).onEach {
        // TODO Save result in dataStore / database
    }.mapToNetworkFlow { it.toDomain() }

    companion object {

        @JvmStatic
        private fun <Remote, Domain> Flow<Resource<Remote>>.mapToNetworkFlow(
            transform: (Remote) -> Domain
        ): Flow<Either<RepositoryError, Domain>> =
            filterNot {
                it.status is Status.Loading
            }.map { resource ->
                when (val status = resource.status) {
                    is Status.EmptySuccess -> status.toRepositoryError().left()
                    is Status.Error -> status.toRepositoryError().left() // TODO We're losing the original "no network" throwable
                    is Status.Loading -> throw IllegalStateException("Unreachable code")
                    is Status.Success -> transform(status.data).right()
                }
            }

    }

}
