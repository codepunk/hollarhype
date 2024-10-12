package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: LocalMessage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<LocalMessage>)

    @Query("SELECT * FROM message WHERE id = :messageId")
    fun getMessage(messageId: Long): Flow<LocalMessage?>

    // endregion Methods
    
}