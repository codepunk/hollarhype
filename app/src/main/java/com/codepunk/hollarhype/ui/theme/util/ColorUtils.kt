package com.codepunk.hollarhype.ui.theme.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

@Suppress("MemberVisibilityCanBePrivate", "unused")
data class Palette(
    val color: Color
) {

    // region Variables

    private val colorHct: FloatArray = FloatArray(3)
            
    val color0: Color
    val color5: Color
    val color10: Color
    val color15: Color
    val color20: Color
    val color25: Color
    val color30: Color
    val color35: Color
    val color40: Color
    val color50: Color
    val color60: Color
    val color70: Color
    val color80: Color
    val color90: Color
    val color95: Color
    val color98: Color
    val color99: Color
    val color100: Color

    // endregion Variables

    // region Constructors

    init {
        ColorUtils.colorToM3HCT(color.toArgb(), colorHct)
        color0 = getColor(0f)
        color5 = getColor(5f)
        color10 = getColor(10f)
        color15 = getColor(15f)
        color20 = getColor(20f)
        color25 = getColor(25f)
        color30 = getColor(30f)
        color35 = getColor(35f)
        color40 = getColor(40f)
        color50 = getColor(50f)
        color60 = getColor(60f)
        color70 = getColor(70f)
        color80 = getColor(80f)
        color90 = getColor(90f)
        color95 = getColor(95f)
        color98 = getColor(98f)
        color99 = getColor(99f)
        color100 = getColor(100f)
    }

    // endregion Constructors

    // region Methods

    fun getColor(tone: Float): Color =
        Color(ColorUtils.M3HCTToColor(colorHct[0], colorHct[1], tone))

    // endregion Methods

}
