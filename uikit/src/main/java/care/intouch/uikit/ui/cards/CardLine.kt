package care.intouch.uikit.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.AccentChips


@Composable
fun CardLine(
    modifier: Modifier = Modifier,
    dateText: String,
    dateColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dateTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    chipText: StringVO,
    chipTextColor: Color,
    chipColors: Color,
    backgroundColor: Color = InTouchTheme.colors.mainBlue,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(334.dp)
            .height(41.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(backgroundColor)
            .padding(horizontal = 14.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = dateText, color = dateColor, style = dateTextStyle)
        AccentChips(
            text = chipText,
            selected = true,
            selectedColor = chipColors,
            unselectedColorText = chipTextColor
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardLine() {
    InTouchTheme {
        CardLine(
            dateText = "May, 15  2023",
            chipText = StringVO.Plain("To do"),
            chipColors = InTouchTheme.colors.accentBeige,
            chipTextColor = InTouchTheme.colors.textGreen.copy(alpha = 0.4f)
        ) {

        }
    }
}