package com.codepunk.hollarhype.ui.theme

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

private const val MEDIUM_CONTRAST = (1f / 3)
private const val HIGH_CONTRAST = (2f / 3)

@Immutable
data class ExtendedColorScheme(
    val quaternary: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
    quaternary = ColorFamily(
        quaternaryLight,
        onQuaternaryLight,
        quaternaryContainerLight,
        onQuaternaryContainerLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    quaternary = ColorFamily(
        quaternaryDark,
        onQuaternaryDark,
        quaternaryContainerDark,
        onQuaternaryContainerDark,
    ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
    quaternary = ColorFamily(
        quaternaryLightMediumContrast,
        onQuaternaryLightMediumContrast,
        quaternaryContainerLightMediumContrast,
        onQuaternaryContainerLightMediumContrast,
    ),
)

val extendedLightHighContrast = ExtendedColorScheme(
    quaternary = ColorFamily(
        quaternaryLightHighContrast,
        onQuaternaryLightHighContrast,
        quaternaryContainerLightHighContrast,
        onQuaternaryContainerLightHighContrast,
    ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
    quaternary = ColorFamily(
        quaternaryDarkMediumContrast,
        onQuaternaryDarkMediumContrast,
        quaternaryContainerDarkMediumContrast,
        onQuaternaryContainerDarkMediumContrast,
    ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
    quaternary = ColorFamily(
        quaternaryDarkHighContrast,
        onQuaternaryDarkHighContrast,
        quaternaryContainerDarkHighContrast,
        onQuaternaryContainerDarkHighContrast,
    ),
)

val fixedColors = FixedColorScheme(
    primaryFixed = HypeGreen,
    onPrimaryFixed = HypeNeutral,
    secondaryFixed = HypeBlue,
    onSecondaryFixed = HypeNeutral,
    tertiaryFixed = HypeOrange,
    onTertiaryFixed = HypeNeutral,
    quaternaryFixed = HypeMagenta,
    onQuaternaryFixed = HypeNeutral,
    primaryFixedDim = primaryLight,
    onPrimaryFixedDim = onPrimaryLight,
    secondaryFixedDim = secondaryLight,
    onSecondaryFixedDim = onSecondaryLight,
    tertiaryFixedDim = tertiaryLight,
    onTertiaryFixedDim = onTertiaryLight,
    quaternaryFixedDim = quaternaryLight,
    onQuaternaryFixedDim = onQuaternaryLight
)

val fixedSizes = FixedSizeScheme(
    border = border,
    borderThick = borderThick,
    paddingSmall = paddingSmall,
    padding = padding,
    paddingLarge = paddingLarge,
    paddingXLarge = paddingXLarge,
    padding2xLarge = padding2xLarge,
    component2xSmall = component2xSmall,
    componentXSmall = componentXSmall,
    componentSmall = componentSmall,
    component = component,
    componentLarge = componentLarge,
    componentXLarge = componentXLarge,
    component2xLarge = component2xLarge,
    region2xSmall = region2xSmall,
    regionXSmall = regionXSmall,
    regionSmall = regionSmall,
    region = region,
    regionLarge = regionLarge,
    regionXLarge = regionXLarge,
    region2xLarge = region2xLarge,
    region3xLarge = region3xLarge
)

val LocalAppColors = staticCompositionLocalOf { lightScheme }

val LocalExtendedColors = staticCompositionLocalOf { extendedLight }

val LocalFixedColors = staticCompositionLocalOf { fixedColors }

val LocalSizes = staticCompositionLocalOf { fixedSizes }

@Composable
fun ExtendedTheme(
    colorScheme: ColorScheme,
    extendedColorScheme: ExtendedColorScheme = LocalExtendedColors.current,
    fixedColorScheme: FixedColorScheme = LocalFixedColors.current,
    fixesSizeScheme: FixedSizeScheme = LocalSizes.current,
    content: @Composable () -> Unit
) {
    val colorCache = remember { colorScheme }
    val extendedColorCache = remember { extendedColorScheme }
    val fixedColorCache = remember { fixedColorScheme }
    val fixedSizeCache = remember { fixesSizeScheme }

    CompositionLocalProvider(
        LocalAppColors provides colorCache,
        LocalExtendedColors provides extendedColorCache,
        LocalFixedColors provides fixedColorCache,
        LocalSizes provides fixedSizeCache,
        content = content
    )
}

@Composable
private fun <S> chooseColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    lightScheme: S,
    darkScheme: S,
    mediumContrastLightColorScheme: S,
    mediumContrastDarkColorScheme: S,
    highContrastLightColorScheme: S,
    highContrastDarkColorScheme: S,
    dynamicChooser: (Context) -> S? = { _ -> null }
): S {
    val context = LocalContext.current
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return if (!dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        if (darkTheme) {
            when {
                uiModeManager.contrast >= HIGH_CONTRAST -> highContrastDarkColorScheme
                uiModeManager.contrast >= MEDIUM_CONTRAST -> mediumContrastDarkColorScheme
                else -> darkScheme
            }
        } else {
            when {
                uiModeManager.contrast >= HIGH_CONTRAST -> highContrastLightColorScheme
                uiModeManager.contrast >= MEDIUM_CONTRAST -> mediumContrastLightColorScheme
                else -> lightScheme
            }
        }
    } else if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicChooser(context)
    } else {
        null
    } ?: if (darkTheme) {
        darkScheme
    } else {
        lightScheme
    }
}

@Composable
fun HollarhypeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = chooseColorScheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        lightScheme = lightScheme,
        darkScheme = darkScheme,
        mediumContrastLightColorScheme = mediumContrastLightColorScheme,
        mediumContrastDarkColorScheme = mediumContrastDarkColorScheme,
        highContrastLightColorScheme = highContrastLightColorScheme,
        highContrastDarkColorScheme = highContrastDarkColorScheme
    ) { context ->
        if (darkTheme) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    }

    val extendedColorScheme = chooseColorScheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        lightScheme = extendedLight,
        darkScheme = extendedDark,
        mediumContrastLightColorScheme = extendedLightMediumContrast,
        mediumContrastDarkColorScheme = extendedDarkMediumContrast,
        highContrastLightColorScheme = extendedLightHighContrast,
        highContrastDarkColorScheme = extendedDarkHighContrast
    )

    ExtendedTheme(
        colorScheme = colorScheme,
        extendedColorScheme = extendedColorScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}
