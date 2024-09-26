package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Locale

@Entity(
    tableName = "user"
)
data class LocalUser(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "first_name")
    val firstName: String = "",
    @ColumnInfo(name = "last_name")
    val lastName: String = "",
    @ColumnInfo(name = "mobile")
    val phoneNumber: String = "",
    @ColumnInfo(name = "email")
    val emailAddress: String = "",
    @ColumnInfo(name = "profile_pic")
    val profilePic: String = "",
    @ColumnInfo(name = "transcribe_enabled")
    val transcribeEnabled: Boolean = false,
    @ColumnInfo(name = "region_code")
    val regionCode: String = Locale.getDefault().country,
    val roles: String = "",
    @ColumnInfo(name = "can_create_campaigns")
    val canCreateCampaigns: Boolean = false,
)
