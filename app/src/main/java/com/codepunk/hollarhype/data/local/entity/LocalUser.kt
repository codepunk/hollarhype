package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(
    tableName = "user"
)
data class LocalUser(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1L,
    @ColumnInfo(name = "first_name")
    val firstName: String = "",
    @ColumnInfo(name = "last_name")
    val lastName: String = "",
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String = "",
    @ColumnInfo(name = "email")
    val emailAddress: String = "",
    @ColumnInfo(name = "profile_pic")
    val profilePic: String = "",
    @ColumnInfo(name = "transcribe_enabled")
    val transcribeEnabled: Boolean = false,
    @ColumnInfo(name = "region_code")
    val region: Region = Region.getDefault(),
    val roles: String = "",
    @ColumnInfo(name = "can_create_campaigns")
    val canCreateCampaigns: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Clock.System.now(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant = Clock.System.now()
)
