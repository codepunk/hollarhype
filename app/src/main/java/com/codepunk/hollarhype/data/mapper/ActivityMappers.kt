package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalActivity
import com.codepunk.hollarhype.data.local.relation.LocalActivityFeedPageActivityCrossRef
import com.codepunk.hollarhype.data.local.relation.LocalActivityFeedPageWithDetails
import com.codepunk.hollarhype.data.local.relation.LocalActivityWithDetails
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

fun RemoteActivity.toLocal(): LocalActivityWithDetails = LocalActivityWithDetails(
    activity = LocalActivity(
        id = id,
        targetId = targetId,
        dataGroupId = data.group?.id,
        dataMessageId = data.message?.id,
        dataRunId = data.run?.id,
        dataUserId = data.user?.id,
        activityText = activityText,
        activityType = activityType.toLocal(),
        userId = userId,
        createdAt = createdAt
    ),
    dataGroup = data.group?.toLocal(),
    dataMessage = data.message?.toLocal(),
    dataRun = data.run?.toLocal(),
    dataUser = data.user?.toLocal()
)

fun LocalActivity.Type.toDomain(): Activity.Type = when (this) {
    LocalActivity.Type.CREATE_GROUP -> Activity.Type.CREATE_GROUP
    LocalActivity.Type.FINISH_RUN -> Activity.Type.FINISH_RUN
    LocalActivity.Type.JOIN_GROUP -> Activity.Type.JOIN_GROUP
    LocalActivity.Type.LIVE -> Activity.Type.LIVE
    LocalActivity.Type.VOICE_MESSAGE -> Activity.Type.VOICE_MESSAGE
}

fun LocalActivityWithDetails.toDomain(): Activity = Activity(
    id = activity.id,
    activityText = activity.activityText,
    activityType = activity.activityType.toDomain(),
    targetId = activity.targetId,
    data = Data(
        group = dataGroup?.toDomain(),
        user = dataUser?.toDomain(),
        message = dataMessage?.toDomain(),
        run = dataRun?.toDomain()
    ),
    createdAt = activity.createdAt
)

fun LocalActivityFeedPageWithDetails.toCrossRefs(): List<LocalActivityFeedPageActivityCrossRef> =
    activities.map {
        LocalActivityFeedPageActivityCrossRef(
            activityFeedPage = activityFeed.page,
            activityId = it.activity.id
        )
    }
