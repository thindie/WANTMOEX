package com.example.core.designelements.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.designelements.R


private val light = Font(R.font.sf_pro, FontWeight.W300)
private val regular = Font(R.font.sf_pro, FontWeight.W400)
private val medium = Font(R.font.sf_pro, FontWeight.W500)
private val semibold = Font(R.font.sf_pro, FontWeight.W600)

private val CryptoViewTypo = FontFamily(fonts = listOf(light, regular, medium, semibold))

val captionTextStyle = TextStyle(
    fontFamily = CryptoViewTypo,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp
)

val CryptoTypoGraphy = Typography(
    displayLarge = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp
    ),
    displayMedium = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W400,
        fontSize = 60.sp
    ),
    displaySmall = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W600,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = CryptoViewTypo,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)
