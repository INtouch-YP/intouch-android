package care.intouch.uikit.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun Toast(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    textColor: Color = InTouchTheme.colors.textBlue
) {
    Box(
        modifier = modifier.background(color = InTouchTheme.colors.mainBlue)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            textAlign = TextAlign.Center,
            style = textStyle,
            color = textColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ToastPreview() {
    InTouchTheme {
        Toast(
            text = "This is a toast",
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth()
                .height(36.dp)
        )
    }
}