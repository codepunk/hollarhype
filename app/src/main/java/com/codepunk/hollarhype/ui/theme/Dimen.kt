package com.codepunk.hollarhype.ui.theme

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.window.core.layout.WindowWidthSizeClass

val touchSize = 48.dp

val xLargePadding = 32.dp
val largePadding = 16.dp
val mediumPadding = 12.dp
val smallPadding = 8.dp
val tinyPadding = 4.dp

val buttonCornerRadius = 6.dp

val smallGutterSize = 16.dp
val largeGutterSize = 32.dp

@Composable
fun currentWindowDpSize(): DpSize = with(LocalDensity.current) {
    if (LocalInspectionMode.current) {
        val dm = LocalContext.current.resources.displayMetrics
        DpSize(dm.widthPixels.toDp(), dm.heightPixels.toDp())
    } else {
        currentWindowSize().toSize().toDpSize()
    }
}

@Composable
fun responsiveMarginWidth(): Dp =
    when (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 16.dp
        WindowWidthSizeClass.MEDIUM -> 32.dp
        WindowWidthSizeClass.EXPANDED -> (currentWindowDpSize().width - 840.dp).div(2f)
        else -> 16.dp
    }

@Composable
fun responsiveColumnCount(): Int =
    when (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 4
        WindowWidthSizeClass.MEDIUM -> 8
        WindowWidthSizeClass.EXPANDED -> 12
        else -> 4
    }

@Composable
fun responsiveColumnWidth(gutterWidth: Dp = smallGutterSize, columnSpan: Int = 1): Dp {
    val currentWindowDpSize = currentWindowDpSize()
    val marginWidth = responsiveMarginWidth()
    val columnCount = responsiveColumnCount()
    val bodyWidth = currentWindowDpSize.width - marginWidth.times(2)
    val totalGutterWidth = gutterWidth.times(columnCount - 1)
    val columnWidth = (bodyWidth - totalGutterWidth).div(columnCount)
    val totalWidth = columnWidth.times(columnSpan) + gutterWidth.times(columnSpan - 1)
    return totalWidth.coerceIn(0.dp, bodyWidth)
}
