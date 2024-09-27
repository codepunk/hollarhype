package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(user: LocalUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUsers(users: List<LocalUser>)

    @Query("SELECT * FROM user WHERE id = :userId")
    abstract fun getUser(userId: Int): Flow<LocalUser?>

    // endregion Methods

}
