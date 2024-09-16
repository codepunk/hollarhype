package com.codepunk.hollarhype.ui.util

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun responsiveLayoutWidth(
    columns: Int,
    gutterWidth: Dp = 16.dp,
    calculateMargins: Boolean = true
): Dp {
    if (columns < 1) return 0.dp

    val dpSize: DpSize = with(LocalDensity.current) {
        if (LocalInspectionMode.current) {
            LocalContext.current.resources.displayMetrics.run {
                DpSize(
                    width = widthPixels.toDp(),
                    height = heightPixels.toDp()
                )
            }
        } else {
            currentWindowSize().toSize().toDpSize()
        }
    }

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val marginWidth = if (!calculateMargins) {
        0.dp
    } else when (adaptiveInfo.windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 16.dp
        WindowWidthSizeClass.MEDIUM -> 32.dp
        WindowWidthSizeClass.EXPANDED -> (dpSize.width - 840.dp) / 2
        else -> 16.dp
    }

    val bodyWidth = dpSize.width - (marginWidth.times(2))

    val totalColumns = when (adaptiveInfo.windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 4
        WindowWidthSizeClass.MEDIUM -> 8
        WindowWidthSizeClass.EXPANDED -> 12
        else -> 4
    }

    val totalGutterWidth = gutterWidth.times(totalColumns - 1)
    val columnWidth = (bodyWidth - totalGutterWidth).div(totalColumns)
    return columnWidth.times(columns) + gutterWidth.times(columns - 1)
}
