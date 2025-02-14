package com.evertonprdo.cornelioprocopio_pr.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.evertonprdo.cornelioprocopio_pr.R

val bodyFontFamily = FontFamily(
    Font(resId = R.font.poppins_regular, weight = FontWeight.Normal),
    Font(resId = R.font.poppins_bold, weight = FontWeight.Bold),
    Font(resId = R.font.poppins_light, weight = FontWeight.Light)
)

val displayFontFamily = FontFamily(
    Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(resId = R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(
        resId = R.font.montserrat_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = baseline.displayMedium.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    displaySmall = baseline.displaySmall.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    headlineLarge = baseline.headlineLarge.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = baseline.headlineMedium.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    headlineSmall = baseline.headlineSmall.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = baseline.titleLarge.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = baseline.titleMedium.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    titleSmall = baseline.titleSmall.copy(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)
