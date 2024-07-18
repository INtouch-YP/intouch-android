package care.intouch.uikit.theme

import androidx.compose.runtime.Immutable
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

@Immutable
data class InTouchTypography(
    val titleAccent: TextStyle = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.32).sp,
    ),
    val titleLarge: TextStyle = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.32).sp,
    ),
    val titleBoldLarge: TextStyle = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 34.sp,
    ),
    val titleMedium: TextStyle = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    ),
    val titleSmall: TextStyle = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    ),
    val subTitle: TextStyle = TextStyle(
        fontFamily = itimRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp,
    ),
    val bodyRegular: TextStyle = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    ),
    val bodySemibold: TextStyle = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    ),
    val bodyBold: TextStyle = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    ),
    val caption1Regular: TextStyle = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    ),
    val caption1Semibold: TextStyle = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    ),
    val caption1Bold: TextStyle = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = (15.5).sp,
    ),
    val caption2Regular: TextStyle = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    ),
    val caption2Semibold: TextStyle = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    ),
    val caption2Bold: TextStyle = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = (15.5).sp,
    ),
    val filtersRegular: TextStyle = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val filtersSemibold: TextStyle = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val filtersBold: TextStyle = TextStyle(
        fontFamily = ralewayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val tabBar: TextStyle = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    ),
)