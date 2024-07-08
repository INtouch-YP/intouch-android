package care.intouch.uikit.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun RowWithMessage(
    textIsGreenOrRed: Boolean,
    messageText: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.caption1Regular,
    colorError: Color = InTouchTheme.colors.errorRed,
    colorSuccess: Color = InTouchTheme.colors.green,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(32.dp)
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = messageText.value(),
            style = textStyle,
            color = if (textIsGreenOrRed) {
                colorSuccess
            } else {
                colorError
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x80338C8B)
@Composable
fun RowWithMessagePreview() {
    InTouchTheme {
        RowWithMessage(
            textIsGreenOrRed = true,
            messageText = StringVO.Plain("resultOfCheckData.message\nresultOfCheckData.message2"),
        )
    }
}