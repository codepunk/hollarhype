package com.codepunk.hollarhype.data.remote.webservice

import arrow.retrofit.adapter.either.ResponseE
import com.codepunk.hollarhype.data.remote.entity.RemoteActivityFeedPage
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthenticateResult
import com.codepunk.hollarhype.data.remote.entity.RemoteLoginResult
import com.codepunk.hollarhype.data.remote.entity.RemoteVerifyResult
import com.codepunk.hollarhype.data.remote.entity.RemoteError
import com.codepunk.hollarhype.data.remote.entity.RemoteSocialProfile
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
    ): ResponseE<RemoteError, RemoteVerifyResult>

    @GET("/athlete/athlete_social_profile")
    suspend fun getUserProfile(): ResponseE<RemoteError, RemoteSocialProfile>

    @GET("/athlete/athlete_social_profile")
    suspend fun authenticate(): ResponseE<RemoteError, RemoteAuthenticateResult>

    @GET("/activity_feed/feed")
    suspend fun activityFeed(
        @Query("device_time") deviceDateTime: LocalDateTime =
            Clock.System.now().toLocalDateTime(timeZone = TimeZone.UTC),
        @Query("page") page: Int = 1
    ): ResponseE<RemoteError, RemoteActivityFeedPage>
}