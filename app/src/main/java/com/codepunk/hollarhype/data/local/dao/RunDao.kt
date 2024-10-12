package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.relation.LocalRunWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RunDao(
    private val database: HollarhypeDatabase
) {

    // region Variables

    private val userDao = database.userDao()
    private val messageDao = database.messageDao()

    // endregion Variables

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRun(run: LocalRun)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRuns(runs: List<LocalRun>)

    @Transaction
    @Query("")
    suspend fun insertRunWithDetails(runWithDetails: LocalRunWithDetails) {
        userDao.insertUser(runWithDetails.user)
        messageDao.insertMessagesWithDetails(runWithDetails.messages)
    }

    @Transaction
    @Query("")
    suspend fun insertRunsWithDetails(
        runsWithDetails: List<LocalRunWithDetails>
    ) {
        runsWithDetails.forEach { insertRunWithDetails(it) }
    }

    @Query("SELECT * FROM run WHERE id = :runId")
    abstract fun getRun(runId: Long): Flow<LocalRunWithDetails?>

    // endregion Methods

}