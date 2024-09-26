package com.codepunk.hollarhype.data.remote.entity

import kotlinx.serialization.SerialName
import java.util.Locale

data class RemoteUser(
    val id: Long,
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
    @SerialName("region_code")
    val regionCode: String = Locale.getDefault().country,
    val roles: String = "",
    @SerialName("can_create_campaigns")
    val canCreateCampaigns: Boolean = false
)
