package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // region Methods

    @Insert
    suspend fun insertUser(user: LocalUser): Long

    @Query("""
        SELECT *
          FROM user
         WHERE id = :userId
    """)
    fun getArtist(userId: Int): Flow<LocalUser?>

    // endregion Methods

}