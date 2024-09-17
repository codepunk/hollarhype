package com.codepunk.hollarhype.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.codepunk.hollarhype.R

val rigidSquare = FontFamily(
    Font(R.font.rigid_square_regular),
    Font(R.font.rigid_square_italic, style = FontStyle.Italic),
    Font(R.font.rigid_square_light, weight = FontWeight.Light),
    Font(R.font.rigid_square_semibold, weight = FontWeight.SemiBold),
    Font(R.font.rigid_square_semibold, weight = FontWeight.Bold)
)

val poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_italic, style = FontStyle.Italic),
    Font(R.font.poppins_thin, weight = FontWeight.Thin),
    Font(R.font.poppins_light, weight = FontWeight.Light),
    Font(R.font.poppins_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.poppins_medium, weight = FontWeight.Medium),
    Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_bold, weight = FontWeight.Bold)
)

val roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_italic, style = FontStyle.Italic),
    Font(R.font.roboto_thin, weight = FontWeight.Thin),
    Font(R.font.roboto_thinitalic, weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.roboto_black, weight = FontWeight.Black),
    Font(R.font.roboto_blackitalic, weight = FontWeight.Black, style = FontStyle.Italic)
)

val futura = FontFamily(
    Font(R.font.futura_medium),
    Font(R.font.futura_medium_italic, style = FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = rigidSquare,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = rigidSquare,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    labelMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),

    labelSmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Italic,
        fontSize = 10.sp
    ),

    displayMedium = TextStyle(
        fontFamily = rigidSquare,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

    displaySmall = TextStyle(
        fontFamily = rigidSquare,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp
    ),

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    displayLarge =,
    headlineSmall =,
    titleLarge =,
    titleMedium =,
    titleSmall =,
    bodySmall =,
    labelLarge =,
    */
)