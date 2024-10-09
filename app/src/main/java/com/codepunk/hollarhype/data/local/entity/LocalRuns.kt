package com.codepunk.hollarhype.data.local.entity

import androidx.room.ColumnInfo

data class LocalRuns(
    val runs: List<LocalRun> = emptyList(),
    @ColumnInfo(name = "local_runs")
    val activeRun: LocalRun? = null,
    val delta: Int = 0,
    @ColumnInfo(name = "next_page")
    val nextPage: Int = 0,
    @ColumnInfo(name = "last_page")
    val lastPage: Boolean = true
)
