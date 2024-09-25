package com.codepunk.hollarhype.util

import android.content.Context
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.ErrorResult

fun ErrorResult.getMessage(context: Context): String =
    cause?.message ?: context.getString(R.string.error_unknown)
