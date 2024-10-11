package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Entity(tableName = "group")
data class LocalGroup(
    @PrimaryKey(autoGenerate = false)
    val id: Long = -1L,
    val name: String = "",
    val desc: String = "",
    val access: String = "",
    @ColumnInfo(name = "request_sent")
    val requestSent: Boolean,
    val status: String?,
    @ColumnInfo(name = "affiliation_profile_pic")
    val profilePic: String?,
    @ColumnInfo(name = "sponsored_expiry_date")
    val sponsoredExpiryDate: LocalDate? = null,
    @ColumnInfo(name = "group_url")
    val groupUrl: String = "",
    @ColumnInfo(name = "is_admin")
    val isAdmin: Boolean = false,
    val searchable: Boolean =true,
    @ColumnInfo(name = "is_full")
    val isFull: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Clock.System.now(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant = Clock.System.now()
)
