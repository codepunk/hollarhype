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

// region Classes

sealed class LayoutSize(val start: Dp, val endExclusive: Dp) {
    val range: OpenEndRange<Dp> = start.rangeUntil(endExclusive)
    val value = start
    val mid: Dp = (range.start + range.endExclusive).div(2f)
}

/**
 * Sizes are based on powers of 2. For useful "in-between" sizes like
 * 48.dp, 96.dp, 192.dp, etc., use LayoutSize.mid.
 */
data object SizeMin : LayoutSize(2.dp, 4.dp)
data object SizeTiny : LayoutSize(4.dp, 8.dp)
data object SizeSmall : LayoutSize(8.dp, 16.dp)
data object SizeMedium : LayoutSize(16.dp, 32.dp)
data object SizeLarge : LayoutSize(32.dp, 64.dp)
data object SizeXLarge : LayoutSize(64.dp, 128.dp)
data object Size2xLarge : LayoutSize(128.dp, 256.dp)
data object Size3xLarge : LayoutSize(256.dp, 512.dp)
data object SizeHuge : LayoutSize(512.dp, 1024.dp)

// endregion Classes

// region Variables

val touchSize = 48.dp

val smallGutterSize = 16.dp
val largeGutterSize = 32.dp

val standardButtonWidth = 192.dp
val buttonCornerRadius = 8.dp

// endregion Variables

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

@Composable
fun windowClassIsExtraLarge(): Boolean = currentWindowDpSize().width >= 1600.dp

// endregion Methods
