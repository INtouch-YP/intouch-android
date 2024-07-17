package care.intouch.uikit.ui.cards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.EmotionalChips

@Composable
fun EmotionDescriptionCard(
    modifier: Modifier,
    text: StringVO,
    selected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color = InTouchTheme.colors.input,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmotionalChips(text = text, selected = selected, onClick = onClick)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmotionDescriptionCard() {
    InTouchTheme {
        var isSelected by remember { mutableStateOf(false) }
        EmotionDescriptionCard(
            modifier = Modifier,
            text = StringVO.Plain("123"),
            selected = false,
            onClick = { isSelected = !isSelected }
        )
    }
}