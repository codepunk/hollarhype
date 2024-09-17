package com.codepunk.hollarhype.util

import arrow.eval.Eval

val <A> Eval<A>.isConsumed: Boolean
    get() = if (this is Lazy<*>) isInitialized() else true

inline fun <A> Eval<A>.consume(onConsume: (A) -> Unit): A =
    if (isConsumed) {
        value()
    } else {
        value().apply {
            onConsume(this)
        }
    }
