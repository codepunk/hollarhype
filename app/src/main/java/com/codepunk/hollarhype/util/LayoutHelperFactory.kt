package com.codepunk.hollarhype.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LayoutHelperFactory @Inject constructor() {
    fun get() = LayoutHelper()
}
