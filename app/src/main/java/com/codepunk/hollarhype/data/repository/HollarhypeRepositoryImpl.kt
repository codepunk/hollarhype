package com.codepunk.hollarhype.data.repository

import android.content.Context
import android.net.ConnectivityManager
import arrow.core.Either
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.Region
import com.codepunk.hollarhype.util.toThrowable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HollarhypeRepositoryImpl(
    @ApplicationContext private val context: Context,
    private val connectivityManager: ConnectivityManager,
    private val webservice: HollarhypeWebservice
) : HollarhypeRepository {

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<Throwable, Boolean>> = flow {
        webservice.login(
            phoneNumber = phoneNumber,
            regionCode = region.regionCode
        ).mapLeft {
            it.toThrowable(context, connectivityManager)
        }.map {
            it.success
        }.also { result ->
            emit(result)
        }
    }

}
