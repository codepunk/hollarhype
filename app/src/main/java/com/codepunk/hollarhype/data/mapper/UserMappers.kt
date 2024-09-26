package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalAuthToken
import com.codepunk.hollarhype.data.local.entity.LocalUser
import com.codepunk.hollarhype.data.local.relation.LocalUserWithAuthToken
import com.codepunk.hollarhype.data.remote.entity.RemoteAuthentication
import com.codepunk.hollarhype.data.remote.entity.RemoteUser
import com.codepunk.hollarhype.domain.model.Authentication
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.util.intl.Region

fun RemoteUser.toLocal(): LocalUser = LocalUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
    profilePic = profilePic,
    transcribeEnabled = transcribeEnabled,
    regionCode = regionCode,
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
    region = Region.of(regionCode),
    roles = roles,
    canCreateCampaigns = canCreateCampaigns
)

fun RemoteAuthentication.toLocal(): LocalUserWithAuthToken = LocalUserWithAuthToken(
    user = user.toLocal(),
    authTokens = setOf(
        LocalAuthToken(
            userId = user.id,
            authToken = authToken
        )
    )
)

fun LocalUserWithAuthToken.toDomain(): Authentication = Authentication(
    user = user.toDomain(),
    authToken = authTokens.firstOrNull()?.authToken
)
