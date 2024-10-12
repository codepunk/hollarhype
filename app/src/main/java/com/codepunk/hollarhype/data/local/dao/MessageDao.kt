package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalMessage
import com.codepunk.hollarhype.data.local.relation.LocalMessageWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MessageDao(
    private val database: HollarhypeDatabase
) {

    // region Variables

    private val userDao = database.userDao()
    private val sponsorDao = database.sponsorDao()

    // endregion Variables

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMessage(message: LocalMessage)

    @Transaction
    @Query("")
    suspend fun insertMessageWithDetails(messageWithDetails: LocalMessageWithDetails) {
        messageWithDetails.sender?.also { sender ->
            userDao.insertUser(sender)
        }
        messageWithDetails.sponsor?.also { sponsor ->
            sponsorDao.insertSponsor(sponsor)
        }
        insertMessage(messageWithDetails.message)
    }

    @Transaction
    @Query("")
    suspend fun insertMessagesWithDetails(
        messagesWithDetails: List<LocalMessageWithDetails>
    ) {
        messagesWithDetails.forEach { insertMessageWithDetails(it) }
    }

    @Query("SELECT * FROM message WHERE id = :messageId")
    abstract fun getMessage(messageId: Long): Flow<LocalMessageWithDetails?>

    // endregion Methods
    
}