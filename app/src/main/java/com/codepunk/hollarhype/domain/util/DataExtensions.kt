package com.codepunk.hollarhype.domain.util

import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.Data

fun Data.getImageUrl(): String? = when {
    group != null -> group.profilePic
    message != null -> message.sender?.profilePic
    run != null -> run.user.profilePic
    user != null -> user.profilePic
    else -> null
}

fun Data.getContentDescriptionResId(): Int = when {
    group != null -> R.string.group
    message != null -> R.string.message
    run != null -> R.string.run
    user != null -> R.string.user
    else -> R.string.unknown
}

fun Data.getPlaceholderDrawableResId(): Int = when {
    group != null -> R.drawable.img_avatar_group
    message != null -> R.drawable.img_avatar_message
    run != null -> R.drawable.img_avatar_run
    else -> R.drawable.img_avatar_user
}
