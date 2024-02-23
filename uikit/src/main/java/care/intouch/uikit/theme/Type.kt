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
    displayLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.32).sp,
    )
)

val TitleLargeTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.32).sp,
    )
)

val TitleMediumTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    )
)

val TitleSmallTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    )
)

val SubTitleTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.32).sp,
    )
)

val BodyRegularTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )
)

val Caption1RegularTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    )
)

val Caption2RegularTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    )
)

val FiltersRegularTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)

val TabBarTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    )
)

//SemiBold
val BodySemiBoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )
)

val Caption1SemiBoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    )
)

val Caption2SemiBoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    )
)

val FiltersSemiBoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)

//Body
val BodyBoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    )
)

val Caption1BoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    )
)

val Caption2BoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    )
)

val FiltersBoldTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
)