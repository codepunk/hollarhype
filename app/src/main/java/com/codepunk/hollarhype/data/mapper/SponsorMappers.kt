package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalSponsor
import com.codepunk.hollarhype.data.remote.entity.RemoteSponsor
import com.codepunk.hollarhype.domain.model.Sponsor

fun RemoteSponsor.toLocal(): LocalSponsor = LocalSponsor(
    id = id,
    name = name,
    textCopy = textCopy,
    url = url,
    sponsorImage = sponsorImage,
    clicks = clicks,
    active = active,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun LocalSponsor.toDomain(): Sponsor = Sponsor(
    id = id,
    name = name,
    textCopy = textCopy,
    url = url,
    sponsorImage = sponsorImage,
    clicks = clicks,
    active = active,
    createdAt = createdAt,
    updatedAt = updatedAt
)
