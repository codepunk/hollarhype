package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalGroup
import com.codepunk.hollarhype.data.remote.entity.RemoteGroup
import com.codepunk.hollarhype.domain.model.Group

fun RemoteGroup.toLocal(): LocalGroup = LocalGroup(
    id = id,
    name = name,
    desc = desc,
    access = access,
    requestSent = requestSent,
    status = status,
    profilePic = profilePic,
    sponsoredExpiryDate = sponsoredExpiryDate,
    groupUrl = groupUrl,
    isAdmin = isAdmin,
    searchable = searchable,
    isFull = isFull,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun LocalGroup.toDomain(): Group = Group(
    id = id,
    name = name,
    desc = desc,
    access = access,
    requestSent = requestSent,
    status = status,
    profilePic = profilePic,
    sponsoredExpiryDate = sponsoredExpiryDate,
    groupUrl = groupUrl,
    isAdmin = isAdmin,
    searchable = searchable,
    isFull = isFull,
    createdAt = createdAt,
    updatedAt = updatedAt
)
