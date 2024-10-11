package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.remote.entity.RemoteActivity
import com.codepunk.hollarhype.domain.model.Activity
import com.codepunk.hollarhype.domain.model.Data

fun RemoteActivity.Type.toLocal(): LocalActivity.Type = when (this) {
    RemoteActivity.Type.CREATE_GROUP -> LocalActivity.Type.CREATE_GROUP
    RemoteActivity.Type.FINISH_RUN -> LocalActivity.Type.FINISH_RUN
    RemoteActivity.Type.JOIN_GROUP -> LocalActivity.Type.JOIN_GROUP
    RemoteActivity.Type.LIVE -> LocalActivity.Type.LIVE
    RemoteActivity.Type.VOICE_MESSAGE -> LocalActivity.Type.VOICE_MESSAGE
}

fun RemoteActivity.toLocal(): LocalActivity = LocalActivity(
    dataGroupId = data.group?.id,
    dataMessageId = data.message?.id,
    dataRunId = data.run?.id,
    dataUserId = data.user?.id,
    targetId = targetId,
    activityText = activityText,
    activityType = activityType.toLocal(),
    createdAt = createdAt
)

/*
fun RemoteActivity.toLocalWithDetails(): LocalActivityWithDetails = LocalActivityWithDetails(
    activity = toLocal(),
    group = data.group?.toLocal(),
    message = data.message?.toLocalWithDetails(),
    run = data.run?.toLocalWithDetails(),
    user = data.user?.toLocal()
)
 */

fun LocalActivity.Type.toDomain(): Activity.Type = when (this) {
    LocalActivity.Type.CREATE_GROUP -> Activity.Type.CREATE_GROUP
    LocalActivity.Type.FINISH_RUN -> Activity.Type.FINISH_RUN
    LocalActivity.Type.JOIN_GROUP -> Activity.Type.JOIN_GROUP
    LocalActivity.Type.LIVE -> Activity.Type.LIVE
    LocalActivity.Type.VOICE_MESSAGE -> Activity.Type.VOICE_MESSAGE
}

fun LocalActivity.toDomain(): Activity = Activity(
    id = id,
    activityText = activityText,
    activityType = activityType.toDomain(),
    targetId = targetId,
    data = Data(),
    createdAt = createdAt
)

/*
fun LocalActivityWithDetails.toDomain(): Activity = Activity(
    id = activity.id,
    activityText = activity.activityText,
    activityType = activity.activityType.toDomain(),
    targetUserId = activity.targetUserId,
    data = Data(
        group = group?.toDomain(),
        user = user?.toDomain(),
        message = message?.toDomain(),
        run = run?.toDomain()
    ),
    createdAt = activity.createdAt
)
 */
