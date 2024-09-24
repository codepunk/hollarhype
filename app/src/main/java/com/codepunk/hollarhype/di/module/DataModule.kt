package com.codepunk.hollarhype.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.data.repository.HollarhypeRepositoryImpl
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // region Methods

    @Singleton
    @Provides
    fun provideHollarhypeRepository(
        @ApplicationContext context: Context,
        connectivityManager: ConnectivityManager,
        webservice: HollarhypeWebservice
    ): HollarhypeRepository = HollarhypeRepositoryImpl(
        context = context,
        connectivityManager = connectivityManager,
        webservice = webservice
    )

    // endregion Methods

}
