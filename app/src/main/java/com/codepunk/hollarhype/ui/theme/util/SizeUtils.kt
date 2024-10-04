package com.codepunk.hollarhype.ui.theme.util

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

abstract class SizeScheme(
    val border: Dp,
    val borderThick: Dp,
    val roundedCorner: Dp,

    val paddingSmall: Dp,
    val padding: Dp,
    val paddingMedium: Dp,
    val paddingLarge: Dp,
    val paddingXLarge: Dp,
    val padding2xLarge: Dp,
    val spacer: Dp,

    val icon: Dp,

    val componentXSmall: Dp,
    val componentSmall: Dp,
    val componentMs: Dp,
    val componentMedium: Dp,
    val componentMl: Dp,
    val componentLarge: Dp,
    val componentXLarge: Dp,

    val regionXSmall: Dp,
    val regionSmall: Dp,
    val regionMs: Dp,
    val regionMedium: Dp,
    val regionMl: Dp,
    val regionLarge: Dp,
    val regionXLarge: Dp,
    val region2XLarge: Dp
)

fun sizeScheme(
    border: Dp = 1.dp,
    borderThick: Dp = 2.dp,
    roundedCorner: Dp = 4.dp,

    paddingSmall: Dp = 4.dp,
    padding: Dp = 8.dp,
    paddingMedium: Dp = 12.dp,
    paddingLarge: Dp = 16.dp,
    paddingXLarge: Dp = 24.dp,
    padding2xLarge: Dp = 32.dp,
    spacer: Dp = 24.dp,

    icon: Dp = 24.dp,

    componentXSmall: Dp = 24.dp,
    componentSmall: Dp = 32.dp,
    componentMs: Dp = 40.dp,
    componentMedium: Dp = 48.dp,
    componentMl: Dp = 56.dp,
    componentLarge: Dp = 64.dp,
    componentXLarge: Dp = 96.dp,

    regionXSmall: Dp = 96.dp,
    regionSmall: Dp = 128.dp,
    regionMs: Dp = 160.dp,
    regionMedium: Dp = 192.dp,
    regionMl: Dp = 224.dp,
    regionLarge: Dp = 256.dp,
    regionXLarge: Dp = 384.dp,
    region2XLarge: Dp = 512.dp
): SizeScheme = object : SizeScheme(
    border = border,
    borderThick = borderThick,
    roundedCorner = roundedCorner,
    paddingSmall = paddingSmall,
    padding = padding,
    paddingMedium = paddingMedium,
    paddingLarge = paddingLarge,
    paddingXLarge = paddingXLarge,
    padding2xLarge = padding2xLarge,
    spacer = spacer,
    icon = icon,
    componentXSmall = componentXSmall,
    componentSmall = componentSmall,
    componentMs = componentMs,
    componentMedium = componentMedium,
    componentMl = componentMl,
    componentLarge = componentLarge,
    componentXLarge = componentXLarge,
    regionXSmall = regionXSmall,
    regionSmall = regionSmall,
    regionMs = regionMs,
    regionMedium = regionMedium,
    regionMl = regionMl,
    regionLarge = regionLarge,
    regionXLarge = regionXLarge,
    region2XLarge = region2XLarge
) {}

// region Methods

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
        takeUnless { LocalInspectionMode.current }
            ?: currentWindowDpSize().let { dpSize ->
                WindowAdaptiveInfo(
                    WindowSizeClass.compute(dpSize.width.value, dpSize.height.value),
                    windowPosture
                )
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

// endregion Methods