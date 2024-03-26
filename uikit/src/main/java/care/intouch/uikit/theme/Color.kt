package care.intouch.uikit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val mainColorBlue = Color(0xFFE1F4F8)
val mainColorGreen = Color(0xFF417D88)
val mainColorGreen40 = Color(0x66417D88)

val accentColorYellow = Color(0xFFFFF5DE)
val accentColorGreen = Color(0xFF338C8B)
val accentColorGreen30 = Color(0x4D338C8B)
val accentColorGreen50 = Color(0x80338C8B)

val textColorBlue = Color(0xFF1F304F)
val textColorBlue50 = Color(0x801F304F)

val textColorGreen = Color(0xFF0B4A56)
val textColorGreen40 = Color(0x66417D88)

val unableElementsColorLight = Color(0xFFEBF4F1)
val unableElementsColorDark = Color(0xFFD9D9D9)

val inputColor = Color(0xFFFFFFFF)
val inputColor40 = Color(0x66FFFFFF)
val inputColor85 = Color(0xD9FFFFFF)

val errorRedColor = Color(0xFFE22749)

@Immutable
data class InTouchColors(
    val mainBlue: Color = mainColorBlue,
    val mainGreen: Color = mainColorGreen,
    val mainGreen40: Color = mainColorGreen40,
    val accentYellow: Color = accentColorYellow,
    val accentGreen: Color = accentColorGreen,
    val accentGreen30: Color = accentColorGreen30,
    val accentGreen50: Color = accentColorGreen50,
    val textBlue: Color = textColorBlue,
    val textBlue50: Color = textColorBlue50,
    val textGreen: Color = textColorGreen,
    val textGreen40: Color = textColorGreen40,
    val unableElementLight: Color = unableElementsColorLight,
    val unableElementDark: Color = unableElementsColorDark,
    val input: Color = inputColor,
    val input40: Color = inputColor40,
    val input85: Color = inputColor85,
    val errorRed: Color = errorRedColor,
)
