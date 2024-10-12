package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: LocalUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<LocalUser>)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUser(userId: Long): Flow<LocalUser?>

    // endregion Methods

}
