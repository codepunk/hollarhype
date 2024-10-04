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

val primaryLight = Color(0xFF456900)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = HypeGreen
val onPrimaryContainerLight = Color(0xFF365400)
val secondaryLight = Color(0xFF0061A2)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = HypeBlue
val onSecondaryContainerLight = Color(0xFF00213C)
val tertiaryLight = Color(0xFF885200)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = HypeOrange
val onTertiaryContainerLight = Color(0xFF533000)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFF5F5F5)
val onBackgroundLight = Color(0xFF191D12)
val surfaceLight = Color(0xFFFCF8F8)
val onSurfaceLight = Color(0xFF1C1B1B)
val surfaceVariantLight = Color(0xFFE2E2E7)
val onSurfaceVariantLight = Color(0xFF45474B)
val outlineLight = Color(0xFF76777B)
val outlineVariantLight = Color(0xFFC6C6CB)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = HypeDark
val inverseOnSurfaceLight = Color(0xFFF4F0EF)
val inversePrimaryLight = Color(0xFF97DA25)
val surfaceDimLight = Color(0xFFDDD9D9)
val surfaceBrightLight = Color(0xFFFCF8F8)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFF6F3F2)
val surfaceContainerLight = Color(0xFFF1EDEC)
val surfaceContainerHighLight = Color(0xFFEBE7E7)
val surfaceContainerHighestLight = Color(0xFFE5E2E1)

val primaryLightMediumContrast = Color(0xFF304A00)
val onPrimaryLightMediumContrast = Color(0xFFFFFFFF)
val primaryContainerLightMediumContrast = Color(0xFF558100)
val onPrimaryContainerLightMediumContrast = Color(0xFFFFFFFF)
val secondaryLightMediumContrast = Color(0xFF004575)
val onSecondaryLightMediumContrast = Color(0xFFFFFFFF)
val secondaryContainerLightMediumContrast = Color(0xFF0078C6)
val onSecondaryContainerLightMediumContrast = Color(0xFFFFFFFF)
val tertiaryLightMediumContrast = Color(0xFF623A00)
val onTertiaryLightMediumContrast = Color(0xFFFFFFFF)
val tertiaryContainerLightMediumContrast = Color(0xFFA56609)
val onTertiaryContainerLightMediumContrast = Color(0xFFFFFFFF)
val errorLightMediumContrast = Color(0xFF8C0009)
val onErrorLightMediumContrast = Color(0xFFFFFFFF)
val errorContainerLightMediumContrast = Color(0xFFDA342E)
val onErrorContainerLightMediumContrast = Color(0xFFFFFFFF)
val backgroundLightMediumContrast = Color(0xFFF7FBE8)
val onBackgroundLightMediumContrast = Color(0xFF191D12)
val surfaceLightMediumContrast = Color(0xFFFCF8F8)
val onSurfaceLightMediumContrast = Color(0xFF1C1B1B)
val surfaceVariantLightMediumContrast = Color(0xFFE2E2E7)
val onSurfaceVariantLightMediumContrast = Color(0xFF414347)
val outlineLightMediumContrast = Color(0xFF5E5F63)
val outlineVariantLightMediumContrast = Color(0xFF797A7F)
val scrimLightMediumContrast = Color(0xFF000000)
val inverseSurfaceLightMediumContrast = Color(0xFF313030)
val inverseOnSurfaceLightMediumContrast = Color(0xFFF4F0EF)
val inversePrimaryLightMediumContrast = Color(0xFF97DA25)
val surfaceDimLightMediumContrast = Color(0xFFDDD9D9)
val surfaceBrightLightMediumContrast = Color(0xFFFCF8F8)
val surfaceContainerLowestLightMediumContrast = Color(0xFFFFFFFF)
val surfaceContainerLowLightMediumContrast = Color(0xFFF6F3F2)
val surfaceContainerLightMediumContrast = Color(0xFFF1EDEC)
val surfaceContainerHighLightMediumContrast = Color(0xFFEBE7E7)
val surfaceContainerHighestLightMediumContrast = Color(0xFFE5E2E1)

val primaryLightHighContrast = Color(0xFF172700)
val onPrimaryLightHighContrast = Color(0xFFFFFFFF)
val primaryContainerLightHighContrast = Color(0xFF304A00)
val onPrimaryContainerLightHighContrast = Color(0xFFFFFFFF)
val secondaryLightHighContrast = Color(0xFF002440)
val onSecondaryLightHighContrast = Color(0xFFFFFFFF)
val secondaryContainerLightHighContrast = Color(0xFF004575)
val onSecondaryContainerLightHighContrast = Color(0xFFFFFFFF)
val tertiaryLightHighContrast = Color(0xFF351D00)
val onTertiaryLightHighContrast = Color(0xFFFFFFFF)
val tertiaryContainerLightHighContrast = Color(0xFF623A00)
val onTertiaryContainerLightHighContrast = Color(0xFFFFFFFF)
val errorLightHighContrast = Color(0xFF4E0002)
val onErrorLightHighContrast = Color(0xFFFFFFFF)
val errorContainerLightHighContrast = Color(0xFF8C0009)
val onErrorContainerLightHighContrast = Color(0xFFFFFFFF)
val backgroundLightHighContrast = Color(0xFFF7FBE8)
val onBackgroundLightHighContrast = Color(0xFF191D12)
val surfaceLightHighContrast = Color(0xFFFCF8F8)
val onSurfaceLightHighContrast = Color(0xFF000000)
val surfaceVariantLightHighContrast = Color(0xFFE2E2E7)
val onSurfaceVariantLightHighContrast = Color(0xFF222428)
val outlineLightHighContrast = Color(0xFF414347)
val outlineVariantLightHighContrast = Color(0xFF414347)
val scrimLightHighContrast = Color(0xFF000000)
val inverseSurfaceLightHighContrast = Color(0xFF313030)
val inverseOnSurfaceLightHighContrast = Color(0xFFFFFFFF)
val inversePrimaryLightHighContrast = Color(0xFFC1FF63)
val surfaceDimLightHighContrast = Color(0xFFDDD9D9)
val surfaceBrightLightHighContrast = Color(0xFFFCF8F8)
val surfaceContainerLowestLightHighContrast = Color(0xFFFFFFFF)
val surfaceContainerLowLightHighContrast = Color(0xFFF6F3F2)
val surfaceContainerLightHighContrast = Color(0xFFF1EDEC)
val surfaceContainerHighLightHighContrast = Color(0xFFEBE7E7)
val surfaceContainerHighestLightHighContrast = Color(0xFFE5E2E1)

val primaryDark = Color(0xFFFFFFFF)
val onPrimaryDark = Color(0xFF223600)
val primaryContainerDark = HypeGreen
val onPrimaryContainerDark = Color(0xFF2D4700)
val secondaryDark = Color(0xFF9DCAFF)
val onSecondaryDark = Color(0xFF003257)
val secondaryContainerDark = HypeBlue
val onSecondaryContainerDark = Color(0xFF00040C)
val tertiaryDark = Color(0xFFFFE0C3)
val onTertiaryDark = Color(0xFF482900)
val tertiaryContainerDark = HypeOrange
val onTertiaryContainerDark = Color(0xFF462700)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF11150A)
val onBackgroundDark = Color(0xFFE0E4D2)
val surfaceDark = Color(0xFF141313)
val onSurfaceDark = Color(0xFFE5E2E1)
val surfaceVariantDark = Color(0xFF45474B)
val onSurfaceVariantDark = Color(0xFFC6C6CB)
val outlineDark = Color(0xFF909095)
val outlineVariantDark = Color(0xFF45474B)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = HypeLight
val inverseOnSurfaceDark = Color(0xFF313030)
val inversePrimaryDark = Color(0xFF456900)
val surfaceDimDark = Color(0xFF141313)
val surfaceBrightDark = Color(0xFF3A3939)
val surfaceContainerLowestDark = Color(0xFF0E0E0E)
val surfaceContainerLowDark = Color(0xFF1C1B1B)
val surfaceContainerDark = Color(0xFF201F1F)
val surfaceContainerHighDark = Color(0xFF2A2A2A)
val surfaceContainerHighestDark = Color(0xFF353434)

val primaryDarkMediumContrast = Color(0xFFFFFFFF)
val onPrimaryDarkMediumContrast = Color(0xFF223600)
val primaryContainerDarkMediumContrast = Color(0xFFA5E835)
val onPrimaryContainerDarkMediumContrast = Color(0xFF152400)
val secondaryDarkMediumContrast = Color(0xFFA5CEFF)
val onSecondaryDarkMediumContrast = Color(0xFF00172D)
val secondaryContainerDarkMediumContrast = Color(0xFF2F9CF7)
val onSecondaryContainerDarkMediumContrast = Color(0xFF000000)
val tertiaryDarkMediumContrast = Color(0xFFFFE0C3)
val onTertiaryDarkMediumContrast = Color(0xFF422500)
val tertiaryContainerDarkMediumContrast = Color(0xFFF9AC50)
val onTertiaryContainerDarkMediumContrast = Color(0xFF000000)
val errorDarkMediumContrast = Color(0xFFFFBAB1)
val onErrorDarkMediumContrast = Color(0xFF370001)
val errorContainerDarkMediumContrast = Color(0xFFFF5449)
val onErrorContainerDarkMediumContrast = Color(0xFF000000)
val backgroundDarkMediumContrast = Color(0xFF11150A)
val onBackgroundDarkMediumContrast = Color(0xFFE0E4D2)
val surfaceDarkMediumContrast = Color(0xFF141313)
val onSurfaceDarkMediumContrast = Color(0xFFFEFAF9)
val surfaceVariantDarkMediumContrast = Color(0xFF45474B)
val onSurfaceVariantDarkMediumContrast = Color(0xFFCACACF)
val outlineDarkMediumContrast = Color(0xFFA2A3A7)
val outlineVariantDarkMediumContrast = Color(0xFF828387)
val scrimDarkMediumContrast = Color(0xFF000000)
val inverseSurfaceDarkMediumContrast = Color(0xFFE5E2E1)
val inverseOnSurfaceDarkMediumContrast = Color(0xFF2A2A2A)
val inversePrimaryDarkMediumContrast = Color(0xFF335000)
val surfaceDimDarkMediumContrast = Color(0xFF141313)
val surfaceBrightDarkMediumContrast = Color(0xFF3A3939)
val surfaceContainerLowestDarkMediumContrast = Color(0xFF0E0E0E)
val surfaceContainerLowDarkMediumContrast = Color(0xFF1C1B1B)
val surfaceContainerDarkMediumContrast = Color(0xFF201F1F)
val surfaceContainerHighDarkMediumContrast = Color(0xFF2A2A2A)
val surfaceContainerHighestDarkMediumContrast = Color(0xFF353434)

val primaryDarkHighContrast = Color(0xFFFFFFFF)
val onPrimaryDarkHighContrast = Color(0xFF000000)
val primaryContainerDarkHighContrast = Color(0xFFA5E835)
val onPrimaryContainerDarkHighContrast = Color(0xFF000000)
val secondaryDarkHighContrast = Color(0xFFFAFAFF)
val onSecondaryDarkHighContrast = Color(0xFF000000)
val secondaryContainerDarkHighContrast = Color(0xFFA5CEFF)
val onSecondaryContainerDarkHighContrast = Color(0xFF000000)
val tertiaryDarkHighContrast = Color(0xFFFFFAF8)
val onTertiaryDarkHighContrast = Color(0xFF000000)
val tertiaryContainerDarkHighContrast = Color(0xFFFFBE76)
val onTertiaryContainerDarkHighContrast = Color(0xFF000000)
val errorDarkHighContrast = Color(0xFFFFF9F9)
val onErrorDarkHighContrast = Color(0xFF000000)
val errorContainerDarkHighContrast = Color(0xFFFFBAB1)
val onErrorContainerDarkHighContrast = Color(0xFF000000)
val backgroundDarkHighContrast = Color(0xFF11150A)
val onBackgroundDarkHighContrast = Color(0xFFE0E4D2)
val surfaceDarkHighContrast = Color(0xFF141313)
val onSurfaceDarkHighContrast = Color(0xFFFFFFFF)
val surfaceVariantDarkHighContrast = Color(0xFF45474B)
val onSurfaceVariantDarkHighContrast = Color(0xFFFBFAFF)
val outlineDarkHighContrast = Color(0xFFCACACF)
val outlineVariantDarkHighContrast = Color(0xFFCACACF)
val scrimDarkHighContrast = Color(0xFF000000)
val inverseSurfaceDarkHighContrast = Color(0xFFE5E2E1)
val inverseOnSurfaceDarkHighContrast = Color(0xFF000000)
val inversePrimaryDarkHighContrast = Color(0xFF1D2F00)
val surfaceDimDarkHighContrast = Color(0xFF141313)
val surfaceBrightDarkHighContrast = Color(0xFF3A3939)
val surfaceContainerLowestDarkHighContrast = Color(0xFF0E0E0E)
val surfaceContainerLowDarkHighContrast = Color(0xFF1C1B1B)
val surfaceContainerDarkHighContrast = Color(0xFF201F1F)
val surfaceContainerHighDarkHighContrast = Color(0xFF2A2A2A)
val surfaceContainerHighestDarkHighContrast = Color(0xFF353434)

val quaternaryLight = Color(0xFFA900A8)
val onQuaternaryLight = Color(0xFFFFFFFF)
val quaternaryContainerLight = HypeMagenta
val onQuaternaryContainerLight = Color(0xFF310030)

val quaternaryLightMediumContrast = Color(0xFF7A007A)
val onQuaternaryLightMediumContrast = Color(0xFFFFFFFF)
val quaternaryContainerLightMediumContrast = Color(0xFFCE00CD)
val onQuaternaryContainerLightMediumContrast = Color(0xFFFFFFFF)

val quaternaryLightHighContrast = Color(0xFF430043)
val onQuaternaryLightHighContrast = Color(0xFFFFFFFF)
val quaternaryContainerLightHighContrast = Color(0xFF7A007A)
val onQuaternaryContainerLightHighContrast = Color(0xFFFFFFFF)

val quaternaryDark = Color(0xFFFFABF3)
val onQuaternaryDark = Color(0xFF5B005B)
val quaternaryContainerDark = HypeMagenta
val onQuaternaryContainerDark = Color(0xFF000000)

val quaternaryDarkMediumContrast = Color(0xFFFFB2F3)
val onQuaternaryDarkMediumContrast = Color(0xFF2F002F)
val quaternaryContainerDarkMediumContrast = Color(0xFFF732F4)
val onQuaternaryContainerDarkMediumContrast = Color(0xFF000000)

val quaternaryDarkHighContrast = Color(0xFFFFF9FA)
val onQuaternaryDarkHighContrast = Color(0xFF000000)
val quaternaryContainerDarkHighContrast = Color(0xFFFFB2F3)
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