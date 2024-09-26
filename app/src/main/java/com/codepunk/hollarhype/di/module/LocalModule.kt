package com.codepunk.hollarhype.di.module

import android.content.Context
import androidx.room.Room
import com.codepunk.hollarhype.BuildConfig
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.dao.AuthTokenDao
import com.codepunk.hollarhype.data.local.dao.UserDao
import com.codepunk.hollarhype.data.local.dao.UserWithAuthTokenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    // region Methods

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HollarhypeDatabase =
        Room.databaseBuilder(
            context = context,
            klass = HollarhypeDatabase::class.java,
            name = BuildConfig.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideAuthTokenDao(database: HollarhypeDatabase): AuthTokenDao = database.authTokenDao()

    @Provides
    @Singleton
    fun provideUserDao(database: HollarhypeDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideUserWithAuthTokenDao(
        database: HollarhypeDatabase
    ): UserWithAuthTokenDao = database.userWithAuthTokenDao()

    // endregion Methods

}