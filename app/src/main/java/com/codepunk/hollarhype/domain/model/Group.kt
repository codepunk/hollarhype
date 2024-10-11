package com.codepunk.hollarhype.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

data class Group(
    val id: Long = -1L,
    val name: String = "",
    val desc: String = "",
    val access: String = "",
    val requestSent: Boolean = false,
    val status: String? = null,
    val profilePic: String? = null,
    val sponsoredExpiryDate: LocalDate? = null,
    val groupUrl: String = "",
    val isAdmin: Boolean = false,
    val searchable: Boolean =true,
    val isFull: Boolean = false,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now(),
)
