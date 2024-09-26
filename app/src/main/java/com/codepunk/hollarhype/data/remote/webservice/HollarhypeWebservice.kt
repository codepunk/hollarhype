package com.codepunk.hollarhype.data.remote.webservice

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.codepunk.hollarhype.data.remote.entity.RemoteLoginResult
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HollarhypeWebservice {

    @FormUrlEncoded
    @POST("/athlete/login")
    suspend fun login(
        @Field("mobile") phoneNumber: String,
        @Field("region_code") regionCode: String
    ): Either<CallError, RemoteLoginResult>

    @FormUrlEncoded
    @POST("/athlete/verify")
    fun verify(
        @Field("mobile") phoneNumber: String,
        @Field("otp") otp: String,
        @Field("region_code") regionCode: String
    ): Either<CallError, RemoteAuthentication>

}