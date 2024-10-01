package com.codepunk.hollarhype.data.remote.entity

import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGroup(
    val id: Long = 0L,
    val name: String = "",
    val desc: String = "",
    @SerialName("affiliation_profile_pic")
    val profilePic: String? = null,
    @SerialName("sponsored_expiry_date")
    @Serializable(with = LocalDateIso8601Serializer::class)
    val sponsoredExpiryDate: LocalDate? = null,
    @SerialName("group_url")
    val groupUrl: String = "",
    @SerialName("is_admin")
    val isAdmin: Boolean = false,
    val searchable: Boolean = false,
    @SerialName("is_full")
    val isFull: Boolean = false,
    @SerialName("admin_email_address")
    val adminEmailAddress: String = ""
)
