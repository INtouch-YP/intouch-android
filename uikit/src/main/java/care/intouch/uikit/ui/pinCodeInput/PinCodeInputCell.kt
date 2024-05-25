import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun PinCodeInputCell(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean,
    textStyle: TextStyle = InTouchTheme.typography.titleAccent,
    textColor: Color = InTouchTheme.colors.textGreen,
    backgroundEmptyColor: Color = InTouchTheme.colors.unableElementLight,
    backgroundFilledColor: Color = InTouchTheme.colors.mainGreen40,
    errorBorderColor: Color = InTouchTheme.colors.errorRed,
    size: Dp = 70.dp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(12.dp)
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = if (text.isNotBlank()) backgroundFilledColor else backgroundEmptyColor,
                shape = roundedCornerShape
            )
            .border(
                width = 1.dp,
                color = if (isError) errorBorderColor else InTouchTheme.colors.transparent,
                shape = roundedCornerShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PinCodeInputCellPreview() {
    InTouchTheme {
        PinCodeInputCell(text = "7", isError = false)
    }
}

@Composable
@Preview(showBackground = true)
fun PinCodeInputCellPreviewEmpty() {
    InTouchTheme {
        PinCodeInputCell(text = "", isError = false)
    }
}

@Composable
@Preview(showBackground = true)
fun PinCodeInputCellPreviewError() {
    InTouchTheme {
        PinCodeInputCell(text = "", isError = true)
    }
}