package com.codepunk.hollarhype.domain.model

import com.codepunk.hollarhype.util.intl.Region

data class User(
    val id: Long = -1L,
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val emailAddress: String = "",
    val profilePic: String = "",
    val transcribeEnabled: Boolean = false,
    val region: Region = Region.getDefault(),
    val roles: String = "",
    val canCreateCampaigns: Boolean = false
)
