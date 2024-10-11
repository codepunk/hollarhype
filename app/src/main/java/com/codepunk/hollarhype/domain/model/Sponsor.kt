package com.codepunk.hollarhype.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Sponsor(
    val runId: Long = -1L,
    val name: String = "",
    val textCopy: String = "",
    val url: String = "",
    val sponsorImage: String? = null,
    val clicks: Int = 0,
    val active: Boolean = false,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now()
)
