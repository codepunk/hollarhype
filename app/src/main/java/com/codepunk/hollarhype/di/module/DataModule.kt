package com.codepunk.hollarhype.di.module

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
        webservice: HollarhypeWebservice
    ): HollarhypeRepository = HollarhypeRepositoryImpl(
        webservice = webservice
    )

    // endregion Methods

}
