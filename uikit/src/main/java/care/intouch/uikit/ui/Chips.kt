package care.intouch.uikit.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
    modifier: Modifier = Modifier,
    text: StringVO,
    initialColor: Color = InTouchTheme.colors.mainBlue,
    selectedColor: Color = InTouchTheme.colors.mainGreen40,
    selectedColorText: Color = InTouchTheme.colors.textGreen,
    onClick: () -> Unit = {}
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(100.dp),
        color = if (isSelected) selectedColor else initialColor,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.bodySemibold,
            color = if (isSelected) InTouchTheme.colors.input else selectedColorText,
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

@Composable
fun AccentChips(
    modifier: Modifier = Modifier,
    text: StringVO,
    alpha: Float = 0.85f,
    initialColor: Color = InTouchTheme.colors.textBlue,
    selectedColor: Color = InTouchTheme.colors.mainGreen40,
    initialColorText: Color = InTouchTheme.colors.input,
    selectedColorText: Color = InTouchTheme.colors.input,
    onClick: () -> Unit = {}
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) selectedColor else initialColor,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.titleSmall,
            color = if (isSelected) initialColorText else selectedColorText,
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

@Composable
fun EmotionalChips(
    modifier: Modifier = Modifier,
    text: StringVO,
    initialColor: Color = InTouchTheme.colors.input,
    selectedColor: Color = InTouchTheme.colors.accentYellow,
    initialColorText: Color = InTouchTheme.colors.textBlue,
    selectedColorText: Color = InTouchTheme.colors.textBlue,
    borderStroke: BorderStroke = BorderStroke(1.dp, InTouchTheme.colors.unableElementLight),
    onClick: () -> Unit = {}
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(15.dp),
        border = borderStroke,
        color = if (isSelected) selectedColor else initialColor,
        modifier = modifier
            .padding(8.dp)

    ) {
        Text(
            text = text.value(),
            style = InTouchTheme.typography.bodyRegular,
            color = if (isSelected) initialColorText else selectedColorText,
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
fun PreviewChips() {
    InTouchTheme {
        Column {
            RegularChips(text = StringVO.Plain("Caption"))
            RegularChips(text = StringVO.Plain("Caption"))
            AccentChips(text = StringVO.Plain("To do"))
            AccentChips(text = StringVO.Plain("In progress"))
            AccentChips(text = StringVO.Plain("Done"))
            EmotionalChips(text = StringVO.Plain("Loss"))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewRowChips() {
//    InTouchTheme {
//        LazyHorizontalGrid(
//            rows = GridCells.Fixed(2),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            item {
//                EmotionalChips(text = StringVO.Plain("Loss"))
//                EmotionalChips(text = StringVO.Plain("Loss"))
////                repeat(6){
////                    EmotionalChips(text = StringVO.Plain("Loss"))
////                }
////                repeat(6){
////                    EmotionalChips(text = StringVO.Plain("Loss"))
////                }
//            }
//        }
//    }
//}
