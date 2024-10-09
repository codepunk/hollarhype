package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MessageDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMessage(message: LocalMessage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMessages(messages: List<LocalMessage>)

    @Query("SELECT * FROM message WHERE id = :messageId")
    abstract fun getMessage(messageId: Long): Flow<LocalMessage?>

    // endregion Methods
    
}