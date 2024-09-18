package com.codepunk.hollarhype.ui.theme

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

val touchSize = 48.dp

val xxxLargePadding = 96.dp
val xxLargePadding = 48.dp
val xLargePadding = 32.dp
val largePadding = 16.dp
val mediumPadding = 12.dp
val smallPadding = 8.dp
val tinyPadding = 4.dp

val standardButtonWidth = 175.dp
val buttonCornerRadius = 6.dp

val tinySize = 8.dp
val smallSize = 16.dp
val mediumSize = 32.dp
val largeSize = 48.dp
val xLargeSize = 96.dp
val xxLargeSize = 128.dp
val xxxLargeSize = 182.dp

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
fun windowClassIsLarge(): Boolean = currentWindowDpSize().width.let { width ->
    width >= 1200.dp && width < 1600.dp
}

@Composable
fun layoutMarginWidth(): Dp = layoutMarginWidth(
    currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
)

fun layoutMarginWidth(sizeClass: WindowWidthSizeClass): Dp =
    when (sizeClass) {
        WindowWidthSizeClass.COMPACT -> 16.dp
        WindowWidthSizeClass.MEDIUM -> 24.dp
        WindowWidthSizeClass.EXPANDED -> 24.dp
        else -> 24.dp
    }

@Composable
fun windowClassIsExtraLarge(): Boolean = currentWindowDpSize().width >= 1600.dp
