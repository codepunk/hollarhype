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
import kotlin.math.pow

// region Classes

enum class LayoutSize(private val factor: Int) {
    MIN(1),         // 2.dp until 4.dp, mid = 3.dp
    TINY(2),        // 4.dp until 8.dp, mid = 6.dp
    SMALL(3),       // 8.dp until 16.dp, mid = 12.dp
    MEDIUM(4),      // 16.dp until 32.dp, mid = 24.dp
    LARGE(5),       // 32.dp until 64.dp, mid = 48.dp
    X_LARGE(6),     // 64.dp until 128.dp, mid = 96.dp
    XX_LARGE(7),    // 128.dp until 256.dp, mid = 192.dp
    XXX_LARGE(8),   // 256.dp until 512.dp, mid = 384.dp
    HUGE(9);        // 512.dp until 1024.dp, mid = 768.dp

    @Suppress("MemberVisibilityCanBePrivate")
    val range: OpenEndRange<Dp> = Dp(2f.pow(factor)).rangeUntil(Dp(2f.pow(factor + 1)))
    val value = range.start
    val mid: Dp = (range.start + range.endExclusive).div(2f)

    override fun toString(): String {
        return "Size(range=$range, value=$value, mid=$mid)"
    }
}

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

// endregion Methods
