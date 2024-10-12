package com.codepunk.hollarhype.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.codepunk.hollarhype.data.local.HollarhypeDatabase
import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.relation.LocalActivityWithDetails
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivity(activity: LocalActivity)

    @Transaction
    @Query("")
    suspend fun insertActivityWithDetails(
        activityWithDetails: LocalActivityWithDetails
    ) {
        activityWithDetails.group?.also { group ->
            groupDao.insertGroup(group)
        }
        activityWithDetails.message?.also { message ->
            messageDao.insertMessageWithDetails(message)
        }
        activityWithDetails.run?.also { run ->
            runDao.insertRunWithDetails(run)
        }
        activityWithDetails.user?.also { user ->
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

    @Query("DELETE FROM activity")
    abstract suspend fun clearActivities()

    @Query("SELECT * FROM activity ORDER BY created_at DESC")
    abstract fun getActivitiesPaginated(): PagingSource<Int, LocalActivityWithDetails>

    // endregion Methods

}