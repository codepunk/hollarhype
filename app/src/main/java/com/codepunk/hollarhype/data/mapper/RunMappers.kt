package com.codepunk.hollarhype.data.mapper

import com.codepunk.hollarhype.data.local.entity.LocalRun
import com.codepunk.hollarhype.data.local.relation.LocalRunWithDetails
import com.codepunk.hollarhype.data.remote.entity.RemoteRun
import com.codepunk.hollarhype.domain.model.Run

fun RemoteRun.Status.toLocal(): LocalRun.Status = when (this) {
    RemoteRun.Status.INACTIVE -> LocalRun.Status.INACTIVE
    RemoteRun.Status.ACTIVE -> LocalRun.Status.ACTIVE
}

fun RemoteRun.toLocal(): LocalRunWithDetails = LocalRunWithDetails(
    run = LocalRun(
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
    ),
    user = user.toLocal(),
    messages = messages.map { it.toLocal() }

)

fun LocalRun.Status.toDomain(): Run.Status = when (this) {
    LocalRun.Status.INACTIVE -> Run.Status.INACTIVE
    LocalRun.Status.ACTIVE -> Run.Status.ACTIVE
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
