package com.codepunk.hollarhype.ui.theme.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

data class Palette(
    val color: Color
) {
    val colors: Map<Int, Color>
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

    init {
        val m3hct = FloatArray(3)
        ColorUtils.colorToM3HCT(color.toArgb(), m3hct)
        color0 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 0f))
        color5 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 5f))
        color10 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 10f))
        color15 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 15f))
        color20 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 20f))
        color25 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 25f))
        color30 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 30f))
        color35 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 35f))
        color40 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 40f))
        color50 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 50f))
        color60 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 60f))
        color70 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 70f))
        color80 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 80f))
        color90 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 90f))
        color95 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 95f))
        color98 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 98f))
        color99 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 99f))
        color100 = Color(ColorUtils.M3HCTToColor(m3hct[0], m3hct[1], 100f))
        colors = mapOf(
            0 to color0,
            5 to color5,
            10 to color10,
            15 to color15,
            20 to color20,
            25 to color25,
            30 to color30,
            35 to color35,
            40 to color40,
            50 to color50,
            60 to color60,
            70 to color70,
            80 to color80,
            90 to color90,
            95 to color95,
            98 to color98,
            99 to color99,
            100 to color100
        )
    }
}