package com.codepunk.hollarhype.domain.model

import com.codepunk.hollarhype.util.intl.Region

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val emailAddress: String = "",
    val region: Region = Region.getDefault(),
    val phoneNumber: String = ""
)
