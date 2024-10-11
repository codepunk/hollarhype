package com.codepunk.hollarhype.data.repository

import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import arrow.core.Either
import arrow.core.Ior
import arrow.core.left
import arrow.core.rightIor
import com.codepunk.hollarhype.data.util.cachedDataResource
import com.codepunk.hollarhype.data.datastore.entity.UserSettings
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.mapper.toDomain
import com.codepunk.hollarhype.data.mapper.toLocal
import com.codepunk.hollarhype.data.mapper.toRepositoryException
import com.codepunk.hollarhype.data.paging.ActivityFeedRemoteMediator
import com.codepunk.hollarhype.data.paging.ActivityFeedRemoteMediatorFactory
import com.codepunk.hollarhype.data.util.networkDataResource
import com.codepunk.hollarhype.data.remote.webservice.HollarhypeWebservice
import com.codepunk.hollarhype.domain.model.Activity
import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.domain.model.Unauthenticated
import com.codepunk.hollarhype.domain.model.UserSession
import com.codepunk.hollarhype.domain.repository.HollarhypeRepository
import com.codepunk.hollarhype.domain.repository.RepositoryException
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

class HollarhypeRepositoryImpl(
    private val connectivityManager: ConnectivityManager,
    private val database: HollarhypeDatabase,
    private val dataStore: DataStore<UserSettings>,
    private val webservice: HollarhypeWebservice,
    private val activityFeedRemoteMediatorFactory: ActivityFeedRemoteMediatorFactory
) : HollarhypeRepository {

    private val userDao by lazy { database.userDao() }

    override fun authenticate(): Flow<Ior<RepositoryException, UserSession>> = flow {
        val userSettings = dataStore.data.firstOrNull()
        val userSession = userSettings?.userSession?.toDomain() ?: Unauthenticated
        if (userSession is Authenticated) {
            this@flow.emitAll(
                cachedDataResource(
                    query = {
                        dataStore.data.map { it.userSession.toDomain() }
                    },
                    fetch = {
                        try {
                            webservice.authenticate().run {
                                body.mapLeft { it.toRepositoryException(code, message) }
                            }
                        } catch (cause: Throwable) { RepositoryException(cause = cause).left() }
                    },
                    shouldEmitCachedWhileFetching = { false },
                    saveFetchResult = { userDao.insertUser(it.user.toLocal()) }
                )
            )
        } else {
            this@flow.emit(userSession.rightIor())
        }
    }

    override fun login(
        phoneNumber: String,
        region: Region
    ): Flow<Either<RepositoryException, Boolean>> = networkDataResource(
        fetch = {
            try {
                webservice.login(
                    phoneNumber = phoneNumber,
                    regionCode = region.regionCode
                ).run {
                    body.mapLeft { it.toRepositoryException(code, message) }
                }
            } catch (cause: Throwable) { RepositoryException(cause = cause).left() }
        }
    ) { it.success }

    override fun verify(
        phoneNumber: String,
        otp: String,
        region: Region
    ): Flow<Either<RepositoryException, UserSession>> = networkDataResource(
        fetch = {
            try {
                webservice.verify(
                    phoneNumber = phoneNumber,
                    otp = otp,
                    regionCode = region.regionCode
                ).run {
                    body.onRight {
                        userDao.insertUser(it.user.toLocal())
                    }.mapLeft {
                        it.toRepositoryException(code, message)
                    }
                }
            } catch (cause: Throwable) { RepositoryException(cause = cause).left() }
        }
    ) {
        it.toDomain()
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun activityFeed(
        deviceDateTime: LocalDateTime
    ): Flow<PagingData<Activity>> = Pager(
        config = PagingConfig(
            pageSize = ACTIVITY_FEED_PAGE_SIZE,
            enablePlaceholders = true
        ),
        remoteMediator = activityFeedRemoteMediatorFactory.create(deviceDateTime),
        pagingSourceFactory = {
            database.activityDao().getActivitiesPaginated()
        }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }

    companion object {

        private const val ACTIVITY_FEED_PAGE_SIZE = 10

    }
}
