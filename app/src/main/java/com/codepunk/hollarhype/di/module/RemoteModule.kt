package com.codepunk.hollarhype.di.module

import android.content.Context
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.codepunk.hollarhype.BuildConfig
import com.codepunk.hollarhype.data.remote.interceptor.HollarhypeAuthInterceptor
import com.codepunk.hollarhype.data.remote.interceptor.HollarhypeUserAgentInterceptor
import com.codepunk.hollarhype.data.remote.interceptor.NetworkConnectionInterceptor
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    // region Methods

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, BuildConfig.OK_HTTP_CLIENT_CACHE_SIZE)

    @Singleton
    @Provides
    fun provideDiscogsOkHttpClient(
        cache: Cache,
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        userAgentInterceptor: HollarhypeUserAgentInterceptor,
        authInterceptor: HollarhypeAuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(networkConnectionInterceptor)
        .addInterceptor(userAgentInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun provideEitherCallAdapterFactory(): EitherCallAdapterFactory =
        EitherCallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideConverterFactory(networkJson: Json): Converter.Factory =
        networkJson.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        eitherCallAdapterFactory: EitherCallAdapterFactory,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(eitherCallAdapterFactory)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideHollarhypeWebService(
        retrofit: Retrofit
    ): HollarhypeWebservice = retrofit.create(HollarhypeWebservice::class.java)

    // endregion Methods

}