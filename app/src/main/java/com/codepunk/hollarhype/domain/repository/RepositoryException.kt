package com.codepunk.hollarhype.domain.repository

class RepositoryException : Exception {

    // region Variables

    override val message: String
        get() = errors.joinToString { it }

    val errors: List<String>

    // endregion Variables

    // region Constructors

    constructor() : super() { this.errors = emptyList() }

    constructor(
        errors: List<String>
    ) : super(
        errors.joinToString { it }
    ) { this.errors = errors }

    constructor(
        cause: Throwable
    ) : super(cause) { this.errors = emptyList() }

    constructor(
        errors: List<String>,
        cause: Throwable
    ) : super(
        errors.joinToString { it },
        cause
    ) { this.errors = errors }

    override fun toString(): String =
        "RepositoryException(errors=$errors, cause='$cause')"

    // endregion Constructors

}
