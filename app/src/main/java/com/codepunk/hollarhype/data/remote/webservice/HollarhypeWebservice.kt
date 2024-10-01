package com.codepunk.hollarhype.data.remote.webservice

import arrow.retrofit.adapter.either.ResponseE
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthenticatedUserResult
import com.codepunk.hollarhype.data.remote.entity.RemoteLoginResult
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import com.codepunk.hollarhype.data.remote.entity.RemoteError
import com.codepunk.hollarhype.data.remote.entity.RemoteSocialProfile
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HollarhypeWebservice {

    @FormUrlEncoded
    @POST("/athlete/login")
    suspend fun login(
        @Field("mobile") phoneNumber: String,
        @Field("region_code") regionCode: String
    ): ResponseE<RemoteError, RemoteLoginResult>

    @FormUrlEncoded
    @POST("/athlete/verify")
    suspend fun verify(
        @Field("mobile") phoneNumber: String,
        @Field("otp") otp: String,
        @Field("region_code") regionCode: String
    ): ResponseE<RemoteError, RemoteAuthentication>

    @GET("/athlete/athlete_social_profile")
    suspend fun getUserProfile(): ResponseE<RemoteError, RemoteSocialProfile>

    @GET("/athlete/athlete_social_profile")
    suspend fun getAuthenticatedUser(): ResponseE<RemoteError, RemoteAuthenticatedUserResult>

}