package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGroup(
    val id: Long = -1L,
    @SerialName("athlete_id")
    val userId: Long? = null,
    val name: String = "",
    val desc: String = "",
    val access: String = "",
    @SerialName("request_sent")
    val requestSent: Boolean,
    val status: String?,
    @SerialName("affiliation_profile_pic")
    val profilePic: String?,
    @SerialName("sponsored_expiry_date")
    val sponsoredExpiryDate: LocalDate? = null,
    @SerialName("group_url")
    val groupUrl: String = "",
    @SerialName("is_admin")
    val isAdmin: Boolean = false,
    val searchable: Boolean =true,
    @SerialName("is_full")
    val isFull: Boolean = false,
    @SerialName("created_at")
    val createdAt: Instant = Clock.System.now(),
    @SerialName("updated_at")
    val updatedAt: Instant = Clock.System.now()
)
