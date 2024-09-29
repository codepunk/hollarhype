package com.codepunk.hollarhype.di.module

import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import com.codepunk.hollarhype.AppCoroutineScope
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.data.repository.HollarhypeRepositoryImpl
import com.codepunk.hollarhype.di.qualifier.IoDispatcher
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.manager.UserSessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // region Methods

    @Singleton
    @Provides
    fun provideHollarhypeRepository(
        connectivityManager: ConnectivityManager,
        database: HollarhypeDatabase,
        dataStore: DataStore<UserSettings>,
        webservice: HollarhypeWebservice,
        userSessionManager: UserSessionManager,
        appCoroutineScope: AppCoroutineScope,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): HollarhypeRepository = HollarhypeRepositoryImpl(
        connectivityManager = connectivityManager,
        dataStore = dataStore,
        database = database,
        webservice = webservice,
        userSessionManager = userSessionManager,
        appCoroutineScope = appCoroutineScope,
        ioDispatcher = ioDispatcher
    )

    // endregion Methods

}
