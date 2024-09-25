package com.codepunk.hollarhype.data.repository

import arrow.core.Either
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.ErrorResult
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.util.intl.Region
import com.codepunk.hollarhype.util.toErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HollarhypeRepositoryImpl(
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

}
