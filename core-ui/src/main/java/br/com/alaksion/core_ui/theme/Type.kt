package br.com.alaksion.core_ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import br.com.alaksion.core_ui.R

private val fontFamily = FontFamily(
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_thin, FontWeight.Thin)
)

internal val Typography = Typography(defaultFontFamily = fontFamily)