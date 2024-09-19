package com.codepunk.hollarhype.ui.theme

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
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
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

val touchSize = 48.dp

val sizeTiny = 4.dp
val sizeSmall = 8.dp
val sizeMediumSmall = 12.dp
val sizeMedium = 16.dp
val sizeMediumLarge = 24.dp
val sizeLarge = 32.dp
val sizeXLarge = 48.dp
val sizeXxLarge = 64.dp
val sizeXxxLarge = 96.dp
val sizeHuge = 128.dp
val sizeXHuge = 192.dp
val sizeXxHuge = 256.dp
val sizeXxxHuge = 384.dp
val sizeGigantic = 512.dp

val smallGutterSize = 16.dp
val largeGutterSize = 32.dp

val standardButtonWidth = 192.dp
val buttonCornerRadius = 8.dp

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
fun currentWindowAdaptiveInfoCustom(): WindowAdaptiveInfo =
    currentWindowAdaptiveInfo().run {
        if (LocalInspectionMode.current) {
            val dpSize = currentWindowDpSize()
            WindowAdaptiveInfo(
                WindowSizeClass.compute(dpSize.width.value, dpSize.height.value),
                windowPosture
            )
        } else {
            this
        }
    }

@Composable
fun layoutMargin(): Dp =
    when (currentWindowAdaptiveInfoCustom().windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> 16.dp
        WindowWidthSizeClass.MEDIUM -> 24.dp
        WindowWidthSizeClass.EXPANDED -> 24.dp
        else -> 24.dp
    }

@Composable
fun windowClassIsExtraLarge(): Boolean = currentWindowDpSize().width >= 1600.dp
