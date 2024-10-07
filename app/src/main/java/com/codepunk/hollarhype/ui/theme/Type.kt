package com.codepunk.hollarhype.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.codepunk.hollarhype.R

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@Suppress("SpellCheckingInspection")
private val futuraFontFamily = FontFamily(
    Font(R.font.futura_medium, weight = FontWeight.Medium),
    Font(R.font.futura_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic)
)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_italic, style = FontStyle.Italic),
    Font(R.font.poppins_thin, weight = FontWeight.Thin),
    Font(R.font.poppins_light, weight = FontWeight.Light),
    Font(R.font.poppins_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.poppins_medium, weight = FontWeight.Medium),
    Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_bold, weight = FontWeight.Bold)
)

val rigidSquareFontFamily = FontFamily(
    Font(R.font.rigid_square_regular),
    Font(R.font.rigid_square_italic, style = FontStyle.Italic),
    Font(R.font.rigid_square_light, weight = FontWeight.Light),
    Font(R.font.rigid_square_semibold, weight = FontWeight.SemiBold),
    Font(R.font.rigid_square_semibold, weight = FontWeight.Bold)
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(
        fontFamily = futuraFontFamily,
        fontStyle = FontStyle.Italic
    ),
    displayMedium = baseline.displayMedium.copy(
        fontFamily = futuraFontFamily,
        fontStyle = FontStyle.Italic
    ),
    displaySmall = baseline.displaySmall.copy(
        fontFamily = futuraFontFamily,
        fontStyle = FontStyle.Italic
    ),
    headlineLarge = baseline.headlineLarge.copy(
        fontFamily = rigidSquareFontFamily
    ),
    headlineMedium = baseline.headlineMedium.copy(
        fontFamily = rigidSquareFontFamily
    ),
    headlineSmall = baseline.headlineSmall.copy(
        fontFamily = rigidSquareFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    titleLarge = baseline.titleLarge,
    titleMedium = baseline.titleMedium,
    titleSmall = baseline.titleSmall,
    bodyLarge = baseline.bodyLarge.copy(
        fontFamily = rigidSquareFontFamily,
        fontWeight = FontWeight.Light
    ),
    bodyMedium = baseline.bodyMedium.copy(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = baseline.bodySmall.copy(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Italic
    ),
    labelLarge = baseline.labelLarge.copy(
        fontFamily = rigidSquareFontFamily,
        fontSize = 22.sp
    ),
    labelMedium = baseline.labelMedium.copy(
        fontFamily = rigidSquareFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp
    ),
    labelSmall = baseline.labelSmall.copy(fontFamily = rigidSquareFontFamily),
)
