package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalSponsor
import com.codepunk.hollarhype.data.remote.entity.RemoteSponsor
import com.codepunk.hollarhype.domain.model.Sponsor

fun RemoteSponsor.toLocal(): LocalSponsor = LocalSponsor(
    runId = runId,
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
    runId = runId,
    name = name,
    textCopy = textCopy,
    url = url,
    sponsorImage = sponsorImage,
    clicks = clicks,
    active = active,
    createdAt = createdAt,
    updatedAt = updatedAt
)
