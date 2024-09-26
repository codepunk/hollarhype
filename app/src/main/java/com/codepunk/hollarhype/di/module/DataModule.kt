package com.codepunk.hollarhype.di.module

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.codepunk.hollarhype.data.local.dao.UserWithAuthTokenDao
import com.codepunk.hollarhype.data.local.relation.LocalUserWithAuthToken
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
        preferencesDataStore: DataStore<Preferences>,
        userWithAuthTokenDao: UserWithAuthTokenDao,
        webservice: HollarhypeWebservice
    ): HollarhypeRepository = HollarhypeRepositoryImpl(
        preferencesDataStore = preferencesDataStore,
        userWithAuthTokenDao = userWithAuthTokenDao,
        webservice = webservice
    )

    // endregion Methods

}
