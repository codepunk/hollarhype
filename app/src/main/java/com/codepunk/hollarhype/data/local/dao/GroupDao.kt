package com.codepunk.hollarhype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.hollarhype.data.local.entity.LocalGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: LocalGroup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroups(groups: List<LocalGroup>)

    @Query("SELECT * FROM `group` WHERE id = :groupId")
    fun getGroup(groupId: Long): Flow<LocalGroup?>

    // endregion Methods

}