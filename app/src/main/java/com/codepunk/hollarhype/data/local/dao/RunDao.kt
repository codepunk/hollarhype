package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalRun
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: LocalRun)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRuns(runs: List<LocalRun>)

    @Query("SELECT * FROM run WHERE id = :runId")
    fun getRun(runId: Long): Flow<LocalRun?>

    // endregion Methods

}