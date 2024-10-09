package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.relation.LocalRunWithDetails
import com.codepunk.hollarhype.data.remote.entity.RemoteRun
import com.codepunk.hollarhype.domain.model.Run

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

fun RemoteRun.toLocalWithDetails(): LocalRunWithDetails = LocalRunWithDetails(
    run = toLocal(),
    user = user.toLocal(),
    messages = messages.map { it.toLocalWithDetails() }
)

fun LocalRun.Status.toDomain(): Run.Status = when (this) {
    LocalRun.Status.INACTIVE -> TODO()
    LocalRun.Status.ACTIVE -> TODO()
}

fun LocalRunWithDetails.toDomain(): Run = Run(
    id = run.id,
    statusMessage = run.statusMessage,
    status = run.status.toDomain(),
    startTime = run.startTime,
    endTime = run.endTime,
    distance = run.distance,
    user = user.toDomain(),
    messages = messages.map { it.toDomain() },
    userId = run.userId,
    enableGps = run.enableGps,
    totalMessageCount = run.totalMessageCount,
    createdAt = run.createdAt,
    updatedAt = run.updatedAt
)
