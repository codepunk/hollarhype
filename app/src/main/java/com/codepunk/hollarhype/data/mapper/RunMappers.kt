package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.remote.entity.RemoteRun
import com.codepunk.hollarhype.domain.model.Run
import com.codepunk.hollarhype.domain.model.User

fun RemoteRun.Status.toLocal(): LocalRun.Status = when (this) {
    RemoteRun.Status.INACTIVE -> LocalRun.Status.INACTIVE
    RemoteRun.Status.ACTIVE -> LocalRun.Status.ACTIVE
}

fun RemoteRun.toLocal(): LocalRun = LocalRun(
    id = id,
    userId = userId,
    statusMessage = statusMessage,
    status = status.toLocal(),
    startTime = startTime,
    endTime = endTime,
    distance = distance,
    enableGps = enableGps,
    totalMessageCount = totalMessageCount,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun LocalRun.Status.toDomain(): Run.Status = when (this) {
    LocalRun.Status.INACTIVE -> TODO()
    LocalRun.Status.ACTIVE -> TODO()
}

/* TODO TEMP
 *  Replace with LocalMessageWithDetails.toDomain()
 */
fun LocalRun.toDomain(): Run = Run(
    id = id,
    statusMessage = statusMessage,
    status = status.toDomain(),
    startTime = startTime,
    endTime = endTime,
    distance = distance,
    user = User(),
    messages = emptyList(),
    userId = userId,
    enableGps = enableGps,
    totalMessageCount = totalMessageCount,
    createdAt = createdAt,
    updatedAt = updatedAt
)
