package care.intouch.uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import care.intouch.uikit.R

val itimRegular = FontFamily(Font(R.font.itim_regular))
val ralewayRegular = FontFamily(Font(R.font.raleway_regular))
val ralewaySemiBold = FontFamily(Font(R.font.raleway_semibold))
val ralewayBold = FontFamily(Font(R.font.raleway_bold))

//Regular
val TitleAccentTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.32).sp,
    )
)

val TitleTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.32).sp,
    ),
    titleMedium = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    ),
    titleSmall = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    )
)

val SubTitleTypography = Typography(
    titleMedium = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.32).sp,
    )
)

val BodyRegularTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )
)

val CaptionRegularTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    ),
    bodySmall = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    )
)

val FiltersRegularTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)

val TabBarTypography = Typography(
    labelSmall = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    )
)

//SemiBold
val BodySemiBoldTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )
)

val CaptionSemiBoldTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    ),
    bodySmall = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    )
)

val FiltersSemiBoldTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)

//Body
val BodyBoldTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )
)

val CaptionBoldTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    ),
    bodySmall = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    )
)

val FiltersBoldTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)