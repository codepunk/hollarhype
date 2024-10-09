package com.codepunk.hollarhype.domain.model

data class Runs(
    val runs: List<Run> = emptyList(),
    val activeRun: Run? = null,
    val delta: Int = 0,
    val nextPage: Int = 0,
    val lastPage: Boolean = true
)
