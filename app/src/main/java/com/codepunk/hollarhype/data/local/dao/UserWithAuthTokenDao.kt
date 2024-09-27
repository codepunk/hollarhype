package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.relation.LocalUserWithAuthToken
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserWithAuthTokenDao(
    private val database: HollarhypeDatabase
) {

    // region Methods

    @Transaction
    @Query("")
    suspend fun insertUserWithAuthToken(
        userWithAuthToken: LocalUserWithAuthToken
    ) {
        // TODO DELETE old auth tokens?
        database.userDao().insertUser(userWithAuthToken.user)
        userWithAuthToken.authTokens.firstOrNull()?.also { authToken ->
            database.authTokenDao().insertAuthToken(authToken)
        }
    }

    @Query("""
        SELECT *
          FROM user
         WHERE id = :userId
    """)
    abstract fun getUserWithAuthToken(userId: Int): Flow<LocalUserWithAuthToken>

    @Query("""
        SELECT *
          FROM user
         WHERE mobile = :phoneNumber
    """)
    abstract fun getUserWithAuthTokenByPhoneNumber(
        phoneNumber: String
    ): Flow<LocalUserWithAuthToken>

    // endregion Methods

}