package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalSponsor
import kotlinx.coroutines.flow.Flow

@Dao
interface SponsorDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSponsor(sponsor: LocalSponsor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSponsors(sponsors: List<LocalSponsor>)

    @Query("SELECT * FROM sponsor WHERE run_id = :runId")
    fun getSponsor(runId: Long): Flow<LocalSponsor?>

    // endregion Methods
    
}