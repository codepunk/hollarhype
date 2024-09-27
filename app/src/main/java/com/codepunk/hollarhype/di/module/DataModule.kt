package com.codepunk.hollarhype.di.module

import androidx.datastore.core.DataStore
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.data.repository.HollarhypeRepositoryImpl
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // region Methods

    @Singleton
    @Provides
    fun provideHollarhypeRepository(
        dataStore: DataStore<UserSettings>,
        database: HollarhypeDatabase,
        webservice: HollarhypeWebservice
    ): HollarhypeRepository = HollarhypeRepositoryImpl(
        dataStore = dataStore,
        database = database,
        webservice = webservice
    )

    // endregion Methods

}
