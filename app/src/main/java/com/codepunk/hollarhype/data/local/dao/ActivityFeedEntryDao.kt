package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
@Dao
interface ActivityFeedEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivityFeedEntry(activityFeed: List<LocalActivityFeedEntry>)

    @Query("SELECT * FROM activity_feed_entry WHERE activity_id = :activityId")
    fun getActivityFeedEntryByActivityId(activityId: Long): Flow<LocalActivityFeedEntry?>

    @Query("DELETE FROM activity_feed_entry")
    suspend fun clearActivityFeedEntries()

}
 */