package care.intouch.uikit.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun RegularChips(
    text: StringVO,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    unselectedColor: Color = InTouchTheme.colors.mainBlue,
    selectedColor: Color = InTouchTheme.colors.mainGreen40,
    unselectedColorText: Color = InTouchTheme.colors.input,
    selectedColorText: Color = InTouchTheme.colors.textGreen,
) {
    var isSelected by rememberSaveable { mutableStateOf(selected) }

    Surface(
        shape = RoundedCornerShape(100.dp),
        color = if (isSelected) selectedColor else unselectedColor,
        modifier = modifier
    ) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.bodySemibold,
            color = if (isSelected) unselectedColorText else selectedColorText,
            modifier = modifier
                .clickable {
                    isSelected = !isSelected
                    onClick()
                }
                .padding(horizontal = 16.dp, vertical = 6.dp),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegularChips() {
    InTouchTheme {
        Column {
            RegularChips(
                text = StringVO.Plain("Caption"),
                onClick = {},
                unselectedColor = Color.White,
                unselectedColorText = InTouchTheme.colors.accentGreen
            )
            Spacer(modifier = Modifier.padding(8.dp))
            RegularChips(text = StringVO.Plain("Caption"), onClick = {}, selected = true)
            Spacer(modifier = Modifier.padding(8.dp))
            RegularChips(text = StringVO.Plain("Caption"), onClick = {})
        }
    }
}

@Composable
fun AccentChips(
    text: StringVO,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    alpha: Float = 0.85f,
    unselectedColor: Color = InTouchTheme.colors.textBlue,
    selectedColor: Color = InTouchTheme.colors.mainGreen40,
    unselectedColorText: Color = InTouchTheme.colors.input,
    selectedColorText: Color = InTouchTheme.colors.input,
) {
    var isSelected by rememberSaveable { mutableStateOf(selected) }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) selectedColor else unselectedColor,
        modifier = modifier
    ) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.titleSmall,
            color = if (isSelected) unselectedColorText else selectedColorText,
            modifier = modifier
                .clickable {
                    isSelected = !isSelected
                    onClick()
                }
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .alpha(alpha),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccentChips() {
    InTouchTheme {
        Column {
            AccentChips(text = StringVO.Plain("In progress"), onClick = {})
            Spacer(modifier = Modifier.padding(8.dp))
            AccentChips(
                text = StringVO.Plain("Done"),
                onClick = {},
                unselectedColor = InTouchTheme.colors.accentGreen50
            )
        }
    }
}

@Composable
fun EmotionalChips(
    text: StringVO,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    unselectedColor: Color = InTouchTheme.colors.input,
    selectedColor: Color = InTouchTheme.colors.accentYellow,
    unselectedColorText: Color = InTouchTheme.colors.textBlue,
    selectedColorText: Color = InTouchTheme.colors.textBlue,
    borderStroke: BorderStroke = BorderStroke(1.dp, InTouchTheme.colors.unableElementLight),
) {
    var isSelected by rememberSaveable { mutableStateOf(selected) }

    Surface(
        shape = RoundedCornerShape(15.dp),
        border = borderStroke,
        color = if (isSelected) selectedColor else unselectedColor,
        modifier = modifier
    ) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.bodyRegular,
            color = if (isSelected) unselectedColorText else selectedColorText,
            modifier = modifier
                .clickable {
                    isSelected = !isSelected
                    onClick()
                }
                .padding(horizontal = 12.dp, vertical = 9.5.dp),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmotionalChips() {
    InTouchTheme {
        Column {
            EmotionalChips(text = StringVO.Plain("Loss"), onClick = {})
            Spacer(modifier = Modifier.padding(8.dp))
            EmotionalChips(text = StringVO.Plain("Loss"), onClick = {}, selected = true)
        }
    }
}