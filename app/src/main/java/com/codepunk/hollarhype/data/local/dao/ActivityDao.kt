package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ActivityDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivity(activity: LocalActivity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivities(activities: List<LocalActivity>): List<Long>

    @Query("SELECT * FROM activity WHERE id = :activityId")
    abstract fun getActivity(activityId: Long): Flow<LocalActivity?>

    // endregion Methods

}