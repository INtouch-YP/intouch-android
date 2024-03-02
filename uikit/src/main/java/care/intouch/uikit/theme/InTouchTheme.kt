package care.intouch.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Immutable
data class InTouchColors(
    val mainColorBlue: Color,
    val mainColorGreen: Color,
    val mainColorGreen40: Color,
    val accentColorYellow: Color,
    val accentColorGreen: Color,
    val accentColorGreen30: Color,
    val accentColorGreen50: Color,
    val textColorBlue: Color,
    val textColorBlue50: Color,
    val textColorGreen: Color,
    val textColorGreen40: Color,
    val unableElementsColorLight: Color,
    val unableElementsColorDark: Color,
    val inputColor: Color,
    val inputColor40: Color,
    val inputColor85: Color,
)

@Immutable
data class InTouchTypography(
    val titleAccentTypography: TextStyle,
    val titleLargeTypography: TextStyle,
    val titleMediumTypography: TextStyle,
    val titleSmallTypography: TextStyle,
    val subTitleTypography: TextStyle,
    val bodyRegularTypography: TextStyle,
    val caption1RegularTypography: TextStyle,
    val caption2RegularTypography: TextStyle,
    val filtersRegularTypography: TextStyle,
    val tabBarTypography: TextStyle,
    val bodySemiBoldTypography: TextStyle,
    val caption1SemiBoldTypography: TextStyle,
    val caption2SemiBoldTypography: TextStyle,
    val filtersSemiBoldTypography: TextStyle,
    val bodyBoldTypography: TextStyle,
    val caption1BoldTypography: TextStyle,
    val caption2BoldTypography: TextStyle,
    val filtersBoldTypography: TextStyle,
)

val LocalInTouchColors = staticCompositionLocalOf {
    InTouchColors(
        mainColorBlue = Color.Unspecified,
        mainColorGreen = Color.Unspecified,
        mainColorGreen40 = Color.Unspecified,
        accentColorYellow = Color.Unspecified,
        accentColorGreen = Color.Unspecified,
        accentColorGreen30 = Color.Unspecified,
        accentColorGreen50 = Color.Unspecified,
        textColorBlue = Color.Unspecified,
        textColorBlue50 = Color.Unspecified,
        textColorGreen = Color.Unspecified,
        textColorGreen40 = Color.Unspecified,
        unableElementsColorLight = Color.Unspecified,
        unableElementsColorDark = Color.Unspecified,
        inputColor = Color.Unspecified,
        inputColor40 = Color.Unspecified,
        inputColor85 = Color.Unspecified
    )

}

val LocalInTouchTypography = staticCompositionLocalOf {
    InTouchTypography(
        titleAccentTypography = TextStyle.Default,
        titleLargeTypography = TextStyle.Default,
        titleMediumTypography = TextStyle.Default,
        titleSmallTypography = TextStyle.Default,
        subTitleTypography = TextStyle.Default,
        bodyRegularTypography = TextStyle.Default,
        caption1RegularTypography = TextStyle.Default,
        caption2RegularTypography = TextStyle.Default,
        filtersRegularTypography = TextStyle.Default,
        tabBarTypography = TextStyle.Default,
        bodySemiBoldTypography = TextStyle.Default,
        caption1SemiBoldTypography = TextStyle.Default,
        caption2SemiBoldTypography = TextStyle.Default,
        filtersSemiBoldTypography = TextStyle.Default,
        bodyBoldTypography = TextStyle.Default,
        caption1BoldTypography = TextStyle.Default,
        caption2BoldTypography = TextStyle.Default,
        filtersBoldTypography = TextStyle.Default,
    )
}

@Composable
fun InTouchTheme(
    content: @Composable () -> Unit
) {
    val inTouchColor = InTouchColors(
        mainColorBlue,
        mainColorGreen,
        mainColorGreen40,
        accentColorYellow,
        accentColorGreen,
        accentColorGreen30,
        accentColorGreen50,
        textColorBlue,
        textColorBlue50,
        textColorGreen,
        textColorGreen40,
        unableElementsColorLight,
        unableElementsColorDark,
        inputColor,
        inputColor40,
        inputColor85
    )

    val inTouchTypography = InTouchTypography(
        titleAccentTypography = TitleAccentTypography.titleLarge,
        titleLargeTypography = TitleTypography.titleLarge,
        titleMediumTypography = TitleTypography.titleMedium,
        titleSmallTypography = TitleTypography.titleSmall,
        subTitleTypography = SubTitleTypography.titleMedium,
        bodyRegularTypography = BodyRegularTypography.bodyMedium,
        caption1RegularTypography = CaptionRegularTypography.bodyMedium,
        caption2RegularTypography = CaptionRegularTypography.bodySmall,
        filtersRegularTypography = FiltersRegularTypography.bodyMedium,
        tabBarTypography = TabBarTypography.labelSmall,
        bodySemiBoldTypography = BodySemiBoldTypography.bodyMedium,
        caption1SemiBoldTypography = CaptionSemiBoldTypography.bodyMedium,
        caption2SemiBoldTypography = CaptionSemiBoldTypography.bodySmall,
        filtersSemiBoldTypography = FiltersSemiBoldTypography.bodyMedium,
        bodyBoldTypography = BodyBoldTypography.bodyMedium,
        caption1BoldTypography = CaptionBoldTypography.bodyMedium,
        caption2BoldTypography = CaptionBoldTypography.bodySmall,
        filtersBoldTypography = FiltersBoldTypography.bodyMedium,
    )
    CompositionLocalProvider(
        LocalInTouchColors provides inTouchColor,
        LocalInTouchTypography provides inTouchTypography,
        content = content
    )
}

object InTouchTheme {
    val colors: InTouchColors
        @Composable
        get() = LocalInTouchColors.current
    val typography: InTouchTypography
        @Composable
        get() = LocalInTouchTypography.current
}