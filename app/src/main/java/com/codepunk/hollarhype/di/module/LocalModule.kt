package com.codepunk.hollarhype.di.module

import android.content.Context
import androidx.room.Room
import com.codepunk.hollarhype.BuildConfig
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.dao.ActivityDao
import com.codepunk.hollarhype.data.local.dao.GroupDao
import com.codepunk.hollarhype.data.local.dao.MessageDao
import com.codepunk.hollarhype.data.local.dao.RunDao
import com.codepunk.hollarhype.data.local.dao.SponsorDao
import com.codepunk.hollarhype.data.local.dao.UserDao
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
    fun provideActivityDao(database: HollarhypeDatabase): ActivityDao = database.activityDao()

    @Provides
    @Singleton
    fun provideGroupDao(database: HollarhypeDatabase): GroupDao = database.groupDao()

    @Provides
    @Singleton
    fun provideMessageDao(database: HollarhypeDatabase): MessageDao = database.messageDao()

    @Provides
    @Singleton
    fun provideRunDao(database: HollarhypeDatabase): RunDao = database.runDao()

    @Provides
    @Singleton
    fun provideSponsorDao(database: HollarhypeDatabase): SponsorDao = database.sponsorDao()

    @Provides
    @Singleton
    fun provideUserDao(database: HollarhypeDatabase): UserDao = database.userDao()

    // endregion Methods

}