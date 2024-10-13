package com.codepunk.hollarhype.domain.util

import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.Activity
import com.codepunk.hollarhype.domain.model.Data

fun Activity.getAvatarUrl(): String? = when(targetId) {
    data.group?.id -> data.group?.profilePic
    data.message?.id -> data.message?.sender?.profilePic
    data.run?.id -> data.run?.user?.profilePic
    data.user?.id -> data.user?.profilePic
    else -> null
}

fun Data.getContentDescriptionResId(): Int = when {
    group != null -> R.string.group
    message != null -> R.string.message
    run != null -> R.string.run
    user != null -> R.string.user
    else -> R.string.unknown
}
