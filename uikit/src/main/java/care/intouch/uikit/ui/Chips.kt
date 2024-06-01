package care.intouch.uikit.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

//region RegularChips
@Composable
fun RegularChips(
    text: StringVO,
    selected: Boolean,
    modifier: Modifier = Modifier,
    unselectedColor: Color = InTouchTheme.colors.mainBlue,
    selectedColor: Color = InTouchTheme.colors.mainGreen40,
    unselectedColorText: Color = InTouchTheme.colors.input,
    selectedColorText: Color = InTouchTheme.colors.textGreen,
    onClick: () -> Unit
) {

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(if (selected) selectedColor else unselectedColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.bodySemibold,
            color = if (selected) unselectedColorText else selectedColorText,
            modifier = modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegularChips() {
    InTouchTheme {
        var isSelected by remember { mutableStateOf(false) }
        var isSelectedSeconds by remember { mutableStateOf(false) }
        Column(modifier = Modifier.padding(20.dp)) {
            RegularChips(
                text = StringVO.Plain("RegularChips"),
                selected = isSelected
            ) {
                isSelected = !isSelected
            }
            Spacer(modifier = Modifier.height(20.dp))
            RegularChips(
                text = StringVO.Plain("RegularChips"),
                selected = isSelectedSeconds,
                unselectedColor = InTouchTheme.colors.input
            ) {
                isSelectedSeconds = !isSelectedSeconds
            }
        }
    }
}
//endregion

//region AccentChips
@Composable
fun AccentChips(
    text: StringVO,
    selected: Boolean,
    modifier: Modifier = Modifier,
    alpha: Float = 0.85f,
    unselectedColor: Color = InTouchTheme.colors.textBlue,
    selectedColor: Color = InTouchTheme.colors.mainGreen40,
    unselectedColorText: Color = InTouchTheme.colors.input,
    selectedColorText: Color = InTouchTheme.colors.input,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) selectedColor else unselectedColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.titleSmall,
            color = if (selected) unselectedColorText else selectedColorText,
            modifier = modifier
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
        var isSelected by remember { mutableStateOf(false) }
        var isSelectedSeconds by remember { mutableStateOf(false) }
        Column(modifier = Modifier.padding(20.dp)) {
            AccentChips(
                text = StringVO.Plain("In progress"),
                selected = isSelected
            ) {
                isSelected = !isSelected
            }
            Spacer(modifier = Modifier.padding(8.dp))
            AccentChips(
                text = StringVO.Plain("Done"),
                selected = isSelectedSeconds,
                unselectedColor = InTouchTheme.colors.mainGreen
            ) {
                isSelectedSeconds = !isSelectedSeconds
            }
        }
    }
}
//endregion

//region EmotionalChips
@Composable
fun EmotionalChips(
    text: StringVO,
    selected: Boolean,
    modifier: Modifier = Modifier,
    unselectedColor: Color = InTouchTheme.colors.input,
    selectedColor: Color = InTouchTheme.colors.accentBeige,
    unselectedColorText: Color = InTouchTheme.colors.textBlue,
    selectedColorText: Color = InTouchTheme.colors.textBlue,
    borderStroke: BorderStroke = BorderStroke(1.dp, InTouchTheme.colors.unableElementDark),
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(borderStroke, shape = RoundedCornerShape(15.dp))
            .background(if (selected) selectedColor else unselectedColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.bodyRegular,
            color = if (selected) unselectedColorText else selectedColorText,
            modifier = modifier.padding(horizontal = 12.dp, vertical = 9.5.dp),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmotionalChips() {
    InTouchTheme {
        var isSelected by remember { mutableStateOf(false) }
        var isSelectedSeconds by remember { mutableStateOf(true) }
        Column(modifier = Modifier.padding(20.dp)) {
            EmotionalChips(
                text = StringVO.Plain("Loss"),
                selected = isSelected
            ) {
                isSelected = !isSelected
            }
            Spacer(modifier = Modifier.padding(8.dp))
            EmotionalChips(
                text = StringVO.Plain("Guilt"),
                selected = isSelectedSeconds
            ) {
                isSelectedSeconds = !isSelectedSeconds
            }
        }
    }
}
//endregion

//region EmotionalChipsSmall
@Composable
fun EmotionalChipsSmall(
    text: StringVO,
    selected: Boolean,
    modifier: Modifier = Modifier,
    unselectedColor: Color = InTouchTheme.colors.input,
    selectedColor: Color = InTouchTheme.colors.accentBeige,
    unselectedColorText: Color = InTouchTheme.colors.textBlue,
    selectedColorText: Color = InTouchTheme.colors.textBlue,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) selectedColor else unselectedColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.caption2Regular,
            color = if (selected) unselectedColorText else selectedColorText,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmotionalChipsSmall() {
    InTouchTheme {
        var isSelected by remember { mutableStateOf(true) }
        var isSelectedSeconds by remember { mutableStateOf(true) }
        Column(modifier = Modifier.padding(20.dp)) {
            EmotionalChipsSmall(
                text = StringVO.Plain("Bad"),
                selected = isSelected
            ) { isSelected = !isSelected }
            Spacer(modifier = Modifier.padding(8.dp))
            EmotionalChipsSmall(
                text = StringVO.Plain("Loneliness"),
                selected = isSelectedSeconds
            ) { isSelectedSeconds = !isSelectedSeconds }
        }
    }
}
//endregion