package br.com.alaksion.core_ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacings(
    val small3: Dp = 4.dp,
    val small2: Dp = 8.dp,
    val small: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 20.dp,
    val large2: Dp = 24.dp,
    val large3: Dp = 28.dp,
    val Xlarge: Dp = 32.dp,
    val Xlarge2: Dp = 36.dp,
    val xLarge3: Dp = 40.dp
)

val LocalSpacings = staticCompositionLocalOf { Spacings() }

object NoteSpacings {

    val spacings: Spacings
        @Composable
        get() = LocalSpacings.current

}

