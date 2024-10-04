package com.codepunk.hollarhype.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.ColorUtils
import com.codepunk.hollarhype.ui.theme.util.Palette

val WhiteSmoke = Color(0xFFF5F5F5)
val BlackSmoke = Color(0xFF0A0A0A)

val HypeGreen = Color(0xFFB4F946)
val HypeBlue = Color(0xFF3BA4FF)
val HypeOrange = Color(0xFFFFB155)
val HypeMagenta = Color(0xFFFF3BFB)
val HypeLight = Color(0xFFF5F5F5)
val HypeDark = Color(0xFF151925)
val HypeError = Color(0xFFFF5449)
val HypeTextDark = Color(0xFF151925)
val HypeTextMedium = Color(0xFF8A8C92)
val HypeTextLight = Color(0xFFE5E5E5)

val hypeGreenPalette = Palette(HypeGreen)
val hypeBluePalette = Palette(HypeBlue)
val hypeOrangePalette = Palette(HypeOrange)
val hypeMagentaPalette = Palette(HypeMagenta)
val hypeDarkPalette = Palette(HypeDark)
val hypeLightPalette = Palette(HypeLight)
val hypeTextMediumPalette = Palette(HypeTextMedium)

/**
 * These values were built by feeding core HollarHype colors
 * into Google's Material Theme Builder
 */

val primaryLight = Color(0xFF000000)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFF23252F)
val onPrimaryContainerLight = Color(0xFFFCF8F9)
val secondaryLight = Color(0xFF456900)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFB8FD4A)
val onSecondaryContainerLight = Color(0xFF365400)
val tertiaryLight = Color(0xFF0061A2)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFF5FAFFF)
val onTertiaryContainerLight = Color(0xFF00213C)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFCF8F9)
val onBackgroundLight = Color(0xFF1C1B1C)
val surfaceLight = Color(0xFFFCF8F8)
val onSurfaceLight = Color(0xFF1C1B1B)
val surfaceVariantLight = Color(0xFFE0E3E4)
val onSurfaceVariantLight = Color(0xFF444748)
val outlineLight = Color(0xFF747879)
val outlineVariantLight = Color(0xFFC4C7C8)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF313030)
val inverseOnSurfaceLight = Color(0xFFF4F0EF)
val inversePrimaryLight = Color(0xFFC4C6D2)
val surfaceDimLight = Color(0xFFDDD9D9)
val surfaceBrightLight = Color(0xFFFCF8F8)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFF6F3F2)
val surfaceContainerLight = Color(0xFFF1EDEC)
val surfaceContainerHighLight = Color(0xFFEBE7E7)
val surfaceContainerHighestLight = Color(0xFFE5E2E1)

val primaryLightMediumContrast = Color(0xFF000000)
val onPrimaryLightMediumContrast = Color(0xFFFFFFFF)
val primaryContainerLightMediumContrast = Color(0xFF23252F)
val onPrimaryContainerLightMediumContrast = Color(0xFFDADCE9)
val secondaryLightMediumContrast = Color(0xFF304A00)
val onSecondaryLightMediumContrast = Color(0xFFFFFFFF)
val secondaryContainerLightMediumContrast = Color(0xFF558100)
val onSecondaryContainerLightMediumContrast = Color(0xFFFFFFFF)
val tertiaryLightMediumContrast = Color(0xFF004575)
val onTertiaryLightMediumContrast = Color(0xFFFFFFFF)
val tertiaryContainerLightMediumContrast = Color(0xFF0078C6)
val onTertiaryContainerLightMediumContrast = Color(0xFFFFFFFF)
val errorLightMediumContrast = Color(0xFF8C0009)
val onErrorLightMediumContrast = Color(0xFFFFFFFF)
val errorContainerLightMediumContrast = Color(0xFFDA342E)
val onErrorContainerLightMediumContrast = Color(0xFFFFFFFF)
val backgroundLightMediumContrast = Color(0xFFFCF8F9)
val onBackgroundLightMediumContrast = Color(0xFF1C1B1C)
val surfaceLightMediumContrast = Color(0xFFFCF8F8)
val onSurfaceLightMediumContrast = Color(0xFF1C1B1B)
val surfaceVariantLightMediumContrast = Color(0xFFE0E3E4)
val onSurfaceVariantLightMediumContrast = Color(0xFF404344)
val outlineLightMediumContrast = Color(0xFF5C6061)
val outlineVariantLightMediumContrast = Color(0xFF787B7C)
val scrimLightMediumContrast = Color(0xFF000000)
val inverseSurfaceLightMediumContrast = Color(0xFF313030)
val inverseOnSurfaceLightMediumContrast = Color(0xFFF4F0EF)
val inversePrimaryLightMediumContrast = Color(0xFFC4C6D2)
val surfaceDimLightMediumContrast = Color(0xFFDDD9D9)
val surfaceBrightLightMediumContrast = Color(0xFFFCF8F8)
val surfaceContainerLowestLightMediumContrast = Color(0xFFFFFFFF)
val surfaceContainerLowLightMediumContrast = Color(0xFFF6F3F2)
val surfaceContainerLightMediumContrast = Color(0xFFF1EDEC)
val surfaceContainerHighLightMediumContrast = Color(0xFFEBE7E7)
val surfaceContainerHighestLightMediumContrast = Color(0xFFE5E2E1)

val primaryLightHighContrast = Color(0xFF000000)
val onPrimaryLightHighContrast = Color(0xFFFFFFFF)
val primaryContainerLightHighContrast = Color(0xFF23252F)
val onPrimaryContainerLightHighContrast = Color(0xFFFFFFFF)
val secondaryLightHighContrast = Color(0xFF172700)
val onSecondaryLightHighContrast = Color(0xFFFFFFFF)
val secondaryContainerLightHighContrast = Color(0xFF304A00)
val onSecondaryContainerLightHighContrast = Color(0xFFFFFFFF)
val tertiaryLightHighContrast = Color(0xFF002440)
val onTertiaryLightHighContrast = Color(0xFFFFFFFF)
val tertiaryContainerLightHighContrast = Color(0xFF004575)
val onTertiaryContainerLightHighContrast = Color(0xFFFFFFFF)
val errorLightHighContrast = Color(0xFF4E0002)
val onErrorLightHighContrast = Color(0xFFFFFFFF)
val errorContainerLightHighContrast = Color(0xFF8C0009)
val onErrorContainerLightHighContrast = Color(0xFFFFFFFF)
val backgroundLightHighContrast = Color(0xFFFCF8F9)
val onBackgroundLightHighContrast = Color(0xFF1C1B1C)
val surfaceLightHighContrast = Color(0xFFFCF8F8)
val onSurfaceLightHighContrast = Color(0xFF000000)
val surfaceVariantLightHighContrast = Color(0xFFE0E3E4)
val onSurfaceVariantLightHighContrast = Color(0xFF212425)
val outlineLightHighContrast = Color(0xFF404344)
val outlineVariantLightHighContrast = Color(0xFF404344)
val scrimLightHighContrast = Color(0xFF000000)
val inverseSurfaceLightHighContrast = Color(0xFF313030)
val inverseOnSurfaceLightHighContrast = Color(0xFFFFFFFF)
val inversePrimaryLightHighContrast = Color(0xFFEAEBF8)
val surfaceDimLightHighContrast = Color(0xFFDDD9D9)
val surfaceBrightLightHighContrast = Color(0xFFFCF8F8)
val surfaceContainerLowestLightHighContrast = Color(0xFFFFFFFF)
val surfaceContainerLowLightHighContrast = Color(0xFFF6F3F2)
val surfaceContainerLightHighContrast = Color(0xFFF1EDEC)
val surfaceContainerHighLightHighContrast = Color(0xFFEBE7E7)
val surfaceContainerHighestLightHighContrast = Color(0xFFE5E2E1)

val primaryDark = Color(0xFFF5F5F5)
val onPrimaryDark = Color(0xFF0A0A0A)
val primaryContainerDark = Color(0xFF9C9EAA)
val onPrimaryContainerDark = Color(0xFF0E111A)
val secondaryDark = Color(0xFFFFFFFF)
val onSecondaryDark = Color(0xFF223600)
val secondaryContainerDark = Color(0xFFA5E835)
val onSecondaryContainerDark = Color(0xFF2D4700)
val tertiaryDark = Color(0xFF9DCAFF)
val onTertiaryDark = Color(0xFF003257)
val tertiaryContainerDark = Color(0xFF2F9CF7)
val onTertiaryContainerDark = Color(0xFF00040C)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF131314)
val onBackgroundDark = Color(0xFFE5E2E2)
val surfaceDark = Color(0xFF141313)
val onSurfaceDark = Color(0xFFE5E2E1)
val surfaceVariantDark = Color(0xFF444748)
val onSurfaceVariantDark = Color(0xFFC4C7C8)
val outlineDark = Color(0xFF8E9192)
val outlineVariantDark = Color(0xFF444748)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFE5E2E1)
val inverseOnSurfaceDark = Color(0xFF313030)
val inversePrimaryDark = Color(0xFF5B5E69)
val surfaceDimDark = Color(0xFF141313)
val surfaceBrightDark = Color(0xFF3A3939)
val surfaceContainerLowestDark = Color(0xFF0E0E0E)
val surfaceContainerLowDark = Color(0xFF1C1B1B)
val surfaceContainerDark = Color(0xFF201F1F)
val surfaceContainerHighDark = Color(0xFF2A2A2A)
val surfaceContainerHighestDark = Color(0xFF353434)

val primaryDarkMediumContrast = Color(0xFFC8CAD7)
val onPrimaryDarkMediumContrast = Color(0xFF13161F)
val primaryContainerDarkMediumContrast = Color(0xFF8E909C)
val onPrimaryContainerDarkMediumContrast = Color(0xFF000000)
val secondaryDarkMediumContrast = Color(0xFFFFFFFF)
val onSecondaryDarkMediumContrast = Color(0xFF223600)
val secondaryContainerDarkMediumContrast = Color(0xFFA5E835)
val onSecondaryContainerDarkMediumContrast = Color(0xFF152400)
val tertiaryDarkMediumContrast = Color(0xFFA5CEFF)
val onTertiaryDarkMediumContrast = Color(0xFF00172D)
val tertiaryContainerDarkMediumContrast = Color(0xFF2F9CF7)
val onTertiaryContainerDarkMediumContrast = Color(0xFF000000)
val errorDarkMediumContrast = Color(0xFFFFBAB1)
val onErrorDarkMediumContrast = Color(0xFF370001)
val errorContainerDarkMediumContrast = Color(0xFFFF5449)
val onErrorContainerDarkMediumContrast = Color(0xFF000000)
val backgroundDarkMediumContrast = Color(0xFF131314)
val onBackgroundDarkMediumContrast = Color(0xFFE5E2E2)
val surfaceDarkMediumContrast = Color(0xFF141313)
val onSurfaceDarkMediumContrast = Color(0xFFFEFAF9)
val surfaceVariantDarkMediumContrast = Color(0xFF444748)
val onSurfaceVariantDarkMediumContrast = Color(0xFFC8CBCC)
val outlineDarkMediumContrast = Color(0xFFA0A3A4)
val outlineVariantDarkMediumContrast = Color(0xFF808485)
val scrimDarkMediumContrast = Color(0xFF000000)
val inverseSurfaceDarkMediumContrast = Color(0xFFE5E2E1)
val inverseOnSurfaceDarkMediumContrast = Color(0xFF2A2A2A)
val inversePrimaryDarkMediumContrast = Color(0xFF454852)
val surfaceDimDarkMediumContrast = Color(0xFF141313)
val surfaceBrightDarkMediumContrast = Color(0xFF3A3939)
val surfaceContainerLowestDarkMediumContrast = Color(0xFF0E0E0E)
val surfaceContainerLowDarkMediumContrast = Color(0xFF1C1B1B)
val surfaceContainerDarkMediumContrast = Color(0xFF201F1F)
val surfaceContainerHighDarkMediumContrast = Color(0xFF2A2A2A)
val surfaceContainerHighestDarkMediumContrast = Color(0xFF353434)

val primaryDarkHighContrast = Color(0xFFFCFAFF)
val onPrimaryDarkHighContrast = Color(0xFF000000)
val primaryContainerDarkHighContrast = Color(0xFFC8CAD7)
val onPrimaryContainerDarkHighContrast = Color(0xFF000000)
val secondaryDarkHighContrast = Color(0xFFFFFFFF)
val onSecondaryDarkHighContrast = Color(0xFF000000)
val secondaryContainerDarkHighContrast = Color(0xFFA5E835)
val onSecondaryContainerDarkHighContrast = Color(0xFF000000)
val tertiaryDarkHighContrast = Color(0xFFFAFAFF)
val onTertiaryDarkHighContrast = Color(0xFF000000)
val tertiaryContainerDarkHighContrast = Color(0xFFA5CEFF)
val onTertiaryContainerDarkHighContrast = Color(0xFF000000)
val errorDarkHighContrast = Color(0xFFFFF9F9)
val onErrorDarkHighContrast = Color(0xFF000000)
val errorContainerDarkHighContrast = Color(0xFFFFBAB1)
val onErrorContainerDarkHighContrast = Color(0xFF000000)
val backgroundDarkHighContrast = Color(0xFF131314)
val onBackgroundDarkHighContrast = Color(0xFFE5E2E2)
val surfaceDarkHighContrast = Color(0xFF141313)
val onSurfaceDarkHighContrast = Color(0xFFFFFFFF)
val surfaceVariantDarkHighContrast = Color(0xFF444748)
val onSurfaceVariantDarkHighContrast = Color(0xFFF9FBFC)
val outlineDarkHighContrast = Color(0xFFC8CBCC)
val outlineVariantDarkHighContrast = Color(0xFFC8CBCC)
val scrimDarkHighContrast = Color(0xFF000000)
val inverseSurfaceDarkHighContrast = Color(0xFFE5E2E1)
val inverseOnSurfaceDarkHighContrast = Color(0xFF000000)
val inversePrimaryDarkHighContrast = Color(0xFF272A33)
val surfaceDimDarkHighContrast = Color(0xFF141313)
val surfaceBrightDarkHighContrast = Color(0xFF3A3939)
val surfaceContainerLowestDarkHighContrast = Color(0xFF0E0E0E)
val surfaceContainerLowDarkHighContrast = Color(0xFF1C1B1B)
val surfaceContainerDarkHighContrast = Color(0xFF201F1F)
val surfaceContainerHighDarkHighContrast = Color(0xFF2A2A2A)
val surfaceContainerHighestDarkHighContrast = Color(0xFF353434)

val quaternaryLight = Color(0xFFA500B2)
val onQuaternaryLight = Color(0xFFFFFFFF)
val quaternaryContainerLight = Color(0xFFF559FF)
val onQuaternaryContainerLight = Color(0xFF230026)

val quaternaryLightMediumContrast = Color(0xFF780081)
val onQuaternaryLightMediumContrast = Color(0xFFFFFFFF)
val quaternaryContainerLightMediumContrast = Color(0xFFC717D6)
val onQuaternaryContainerLightMediumContrast = Color(0xFFFFFFFF)

val quaternaryLightHighContrast = Color(0xFF420047)
val onQuaternaryLightHighContrast = Color(0xFFFFFFFF)
val quaternaryContainerLightHighContrast = Color(0xFF780081)
val onQuaternaryContainerLightHighContrast = Color(0xFFFFFFFF)

val quaternaryDark = Color(0xFFFFA9FD)
val onQuaternaryDark = Color(0xFF590061)
val quaternaryContainerDark = Color(0xFFC717D6)
val onQuaternaryContainerDark = Color(0xFFFFFFFF)

val quaternaryDarkMediumContrast = Color(0xFFFFB0FC)
val onQuaternaryDarkMediumContrast = Color(0xFF2E0032)
val quaternaryContainerDarkMediumContrast = Color(0xFFE944F5)
val onQuaternaryContainerDarkMediumContrast = Color(0xFF000000)

val quaternaryDarkHighContrast = Color(0xFFFFF9FA)
val onQuaternaryDarkHighContrast = Color(0xFF000000)
val quaternaryContainerDarkHighContrast = Color(0xFFFFB0FC)
val onQuaternaryContainerDarkHighContrast = Color(0xFF000000)

@Composable
private fun PreviewColor(
    modifier: Modifier = Modifier,
    color: Color,
    palette: Palette,
    title: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizes.padding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color)
        ) {
            val lum = ColorUtils.calculateLuminance(color.toArgb())
            Text(
                modifier = Modifier
                    .padding(sizes.padding)
                    .align(Alignment.BottomEnd),
                text = title,
                color = if (lum < 0.5f) Color.White else Color.Black
            )
        }

        HorizontalDivider()

        palette.colors.forEach {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(it.value)
            ) {
                val lum = ColorUtils.calculateLuminance(it.value.toArgb())
                Text(
                    modifier = Modifier
                        .padding(sizes.padding)
                        .align(Alignment.BottomEnd),
                    text = it.key.toString(),
                    color = if (lum < 0.5f) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewColors() {
    HollarhypeTheme {
        Scaffold { padding ->
            Row(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(sizes.padding),
                horizontalArrangement = Arrangement.spacedBy(sizes.padding)
            ) {
                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeGreen,
                    palette = hypeGreenPalette,
                    title = "HypeGreen"
                )

                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeBlue,
                    palette = hypeBluePalette,
                    title = "HypeBlue"
                )

                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeOrange,
                    palette = hypeOrangePalette,
                    title = "HypeOrange"
                )

                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeMagenta,
                    palette = hypeMagentaPalette,
                    title = "HypeMagenta"
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewGrayscale() {
    HollarhypeTheme {
        Scaffold { padding ->
            Row(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(sizes.padding),
                horizontalArrangement = Arrangement.spacedBy(sizes.padding)
            ) {
                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeDark,
                    palette = hypeDarkPalette,
                    title = "HypeDark"
                )

                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeLight,
                    palette = hypeLightPalette,
                    title = "HypeLight"
                )

                PreviewColor(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    color = HypeTextMedium,
                    palette = hypeTextMediumPalette,
                    title = "HypeTextMedium"
                )
            }
        }
    }
}