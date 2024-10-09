package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalUser
import com.codepunk.hollarhype.data.remote.entity.RemoteUser
import com.codepunk.hollarhype.domain.model.User

fun RemoteUser.toLocal(): LocalUser = LocalUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
    profilePic = profilePic,
    transcribeEnabled = transcribeEnabled,
    region = region,
    roles = roles,
    canCreateCampaigns = canCreateCampaigns
)

fun LocalUser.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
    profilePic = profilePic,
    transcribeEnabled = transcribeEnabled,
    region = region,
    roles = roles,
    canCreateCampaigns = canCreateCampaigns
)
