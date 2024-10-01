package com.codepunk.hollarhype.domain.model

data class Run(
    val id: Long = 0L
) {

    // region Nested & inner classes

    enum class Status {
        INACTIVE,
        ACTIVE
    }

    // endregion Nested & inner classes

}
