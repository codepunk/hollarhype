package com.codepunk.hollarhype.data.remote.webservice

import com.codepunk.hollarhype.data.remote.entity.RemoteLoginResult
import com.codepunk.hollarhype.data.remote.entity.RemoteVerifyResult
import com.hadiyarajesh.flower_core.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HollarhypeWebservice {

    @FormUrlEncoded
    @POST("/athlete/login")
    suspend fun login(
        @Field("mobile") phoneNumber: String,
        @Field("region_code") regionCode: String
    ): ApiResponse<RemoteLoginResult>

    @FormUrlEncoded
    @POST("/athlete/verify")
    suspend fun verify(
        @Field("mobile") phoneNumber: String,
        @Field("otp") otp: String,
        @Field("region_code") regionCode: String
    ): ApiResponse<RemoteVerifyResult>

}