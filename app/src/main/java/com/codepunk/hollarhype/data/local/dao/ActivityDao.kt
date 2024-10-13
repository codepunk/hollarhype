package com.codepunk.hollarhype.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.entity.LocalActivityFeedPage
import com.codepunk.hollarhype.data.local.relation.LocalActivityFeedPageActivityCrossRef
import com.codepunk.hollarhype.data.local.relation.LocalActivityFeedPageWithDetails
import com.codepunk.hollarhype.data.local.relation.LocalActivityWithDetails
import com.codepunk.hollarhype.data.mapper.toCrossRefs
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ActivityDao(
    private val database: HollarhypeDatabase
) {

    // region Variables

    private val groupDao = database.groupDao()
    private val messageDao = database.messageDao()
    private val runDao = database.runDao()
    private val userDao = database.userDao()

    // endregion Variables

    // region Methods

    // Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivity(activity: LocalActivity)

    @Transaction
    @Query("")
    suspend fun insertActivityWithDetails(
        activityWithDetails: LocalActivityWithDetails
    ) {
        activityWithDetails.dataGroup?.also { group ->
            groupDao.insertGroup(group)
        }
        activityWithDetails.dataMessage?.also { message ->
            messageDao.insertMessageWithDetails(message)
        }
        activityWithDetails.dataRun?.also { run ->
            runDao.insertRunWithDetails(run)
        }
        activityWithDetails.dataUser?.also { user ->
            userDao.insertUser(user)
        }
        return insertActivity(activityWithDetails.activity)
    }

    @Transaction
    @Query("")
    suspend fun insertActivitiesWithDetails(
        activitiesWithDetails: List<LocalActivityWithDetails>
    ) {
        activitiesWithDetails.forEach { insertActivityWithDetails(it) }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivityFeedPage(activityFeed: LocalActivityFeedPage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivityFeedPageActivityCrossRefs(
        crossRefs: List<LocalActivityFeedPageActivityCrossRef>
    )

    @Transaction
    @Query("")
    suspend fun insertActivityFeedPageWithDetails(
        activityFeedWithDetails: LocalActivityFeedPageWithDetails
    ) {
        insertActivitiesWithDetails(activityFeedWithDetails.activities)
        activityFeedWithDetails.activeRun?.also { activeRun ->
            runDao.insertRunWithDetails(activeRun)
        }
        insertActivityFeedPage(activityFeedWithDetails.activityFeed)
        insertActivityFeedPageActivityCrossRefs(activityFeedWithDetails.toCrossRefs())
    }

    // Select

    @Query("SELECT * FROM activity ORDER BY created_at DESC")
    abstract fun getActivitiesPaginated(): PagingSource<Int, LocalActivityWithDetails>

    @Query("""
        SELECT f.*
        FROM activity_feed f, 
            activity_feed_page_activity_cross_ref x
        WHERE f.page = x.activity_feed_page
        AND x.activity_id = :activityId
    """)
    abstract fun getActivityFeedPageByActivityId(activityId: Long): Flow<LocalActivityFeedPage?>

    // Delete

    @Query("DELETE FROM activity")
    abstract suspend fun clearActivities()

    @Query("DELETE FROM activity_feed")
    abstract suspend fun clearActivityFeed()

    @Transaction
    @Query("")
    suspend fun clearActivityFeedAndActivities() {
        clearActivityFeed()
        clearActivities()
    }

    // endregion Methods

}