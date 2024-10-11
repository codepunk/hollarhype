package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeedEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityFeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivityFeed(activityFeed: List<LocalActivityFeedEntry>)

    @Query("SELECT * FROM activity_feed WHERE activity_id = :activityId")
    fun getActivityFeedByActivityId(activityId: Long): Flow<LocalActivityFeedEntry?>

    @Query("DELETE FROM activity_feed")
    suspend fun clearActivityFeed()

}