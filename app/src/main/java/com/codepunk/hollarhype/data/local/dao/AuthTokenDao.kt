package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.codepunk.hollarhype.data.local.entity.LocalAuthToken

@Dao
interface AuthTokenDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAuthToken(authToken: LocalAuthToken)

    @Delete
    abstract suspend fun deleteAuthToken(authToken: LocalAuthToken)

    // endregion Methods

}