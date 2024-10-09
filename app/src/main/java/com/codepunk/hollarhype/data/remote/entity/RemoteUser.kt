package com.codepunk.hollarhype.data.remote.entity

import com.codepunk.hollarhype.data.remote.serializer.RegionSerializer
import com.codepunk.hollarhype.util.intl.Region
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUser(
    val id: Long = -1,
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("mobile")
    val phoneNumber: String = "",
    @SerialName("email")
    val emailAddress: String = "",
    @SerialName("profile_pic")
    val profilePic: String = "",
    @SerialName("transcribe_enabled")
    val transcribeEnabled: Boolean = false,
    @Serializable(with = RegionSerializer::class)
    @SerialName("region_code")
    val region: Region = Region.getDefault(),
    val roles: String = "",
    @SerialName("can_create_campaigns")
    val canCreateCampaigns: Boolean = false,
    @SerialName("created_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val createdAt: Instant = Clock.System.now(),
    @SerialName("updated_at")
    @Serializable(with = InstantIso8601Serializer::class)
    val updatedAt: Instant = Clock.System.now()
)
