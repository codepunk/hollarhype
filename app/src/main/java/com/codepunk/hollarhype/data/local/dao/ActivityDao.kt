package com.codepunk.hollarhype.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.relation.LocalActivityWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: LocalActivity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivities(activities: List<LocalActivity>): List<Long>

    @Query("DELETE FROM activity")
    suspend fun clearActivities()

    @Query("SELECT * FROM activity WHERE id = :activityId")
    fun getActivity(activityId: Long): Flow<LocalActivity?>

    @Query("SELECT * FROM activity ORDER BY created_at DESC")
    fun getAllActivities(): Flow<LocalActivity>

    @Query("SELECT * FROM activity ORDER BY created_at DESC")
    fun getActivitiesPaginated(): PagingSource<Int, LocalActivityWithDetails>

    // endregion Methods

}