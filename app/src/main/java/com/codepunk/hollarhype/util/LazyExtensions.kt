package com.codepunk.hollarhype.util

val <T> Lazy<T>.isConsumed: Boolean
    get() = isInitialized()

inline fun <T> Lazy<T>.consume(onConsume: (T) -> Unit): T = if (isConsumed) {
    value
} else {
    value.apply {
        onConsume(this)
    }
}
