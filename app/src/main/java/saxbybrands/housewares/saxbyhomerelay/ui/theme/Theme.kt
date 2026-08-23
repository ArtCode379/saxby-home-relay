package saxbybrands.housewares.saxbyhomerelay.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SaxbyColors =
    lightColorScheme(
        primary = SaxbyTerracotta,
        onPrimary = SaxbySurface,
        secondary = SaxbyTeal,
        onSecondary = SaxbySurface,
        background = SaxbyIvory,
        onBackground = SaxbyInk,
        surface = SaxbySurface,
        onSurface = SaxbyInk,
        surfaceVariant = Color(0xFFF3E9E1),
        onSurfaceVariant = SaxbyMuted,
        outline = SaxbyBorder,
        error = Color(0xFFBA1A1A),
    )

@Composable
fun ProductAppRQDMVTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(colorScheme = SaxbyColors, typography = AppTypography, content = content)
}
