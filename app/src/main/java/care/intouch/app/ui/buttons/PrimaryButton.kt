package care.intouch.app.ui.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    textStyle: TextStyle,
    isEnabled: Boolean,
    isHasStroke: Boolean,
    enableBackgroundColor: Color,
    disableBackgroundColor: Color,
    enableTextColor: Color,
    disableTextColor: Color,
    borderStrokeColor: Color,
) {
    Button(
        modifier = modifier,
        border = if (isHasStroke) {
            BorderStroke(1.dp, borderStrokeColor)
        } else {
            BorderStroke(0.dp, InTouchTheme.colors.inputColor)
        },
        enabled = isEnabled,
        colors = ButtonColors(
            containerColor = enableBackgroundColor,
            contentColor = enableTextColor,
            disabledContainerColor = disableBackgroundColor,
            disabledContentColor = disableTextColor,
        ), onClick = { onClick.invoke() }
    )
    {
        Text(text = text, style = textStyle)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPrimaryButton() {
    val modifier = Modifier
        .width(262.dp)
        .height(69.dp)
        .padding(5.dp)
    InTouchTheme {
        PrimaryButton(
            onClick = { },
            modifier = modifier,
            text = "Call to action",
            textStyle = InTouchTheme.typography.titleMediumTypography,
            isEnabled = true,
            isHasStroke = false,
            enableBackgroundColor = InTouchTheme.colors.mainColorGreen,
            disableBackgroundColor = InTouchTheme.colors.unableElementsColorLight,
            enableTextColor = InTouchTheme.colors.inputColor,
            disableTextColor = InTouchTheme.colors.textColorGreen40,
            borderStrokeColor = InTouchTheme.colors.textColorGreen40
        )
    }
}