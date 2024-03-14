package care.intouch.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalInTouchColors = staticCompositionLocalOf {
    InTouchColors()
}

val LocalInTouchTypography = staticCompositionLocalOf {
    InTouchTypography()
}

object InTouchTheme {
    val typography: InTouchTypography
        @Composable
        get() = LocalInTouchTypography.current
    val colors: InTouchColors
        @Composable
        get() = LocalInTouchColors.current
}

@Composable
fun InTouchTheme(
    typography: InTouchTypography = InTouchTheme.typography,
    colors: InTouchColors = InTouchTheme.colors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalInTouchColors provides colors,
        LocalInTouchTypography provides typography,
        content = content
    )
}