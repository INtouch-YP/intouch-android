package care.intouch.uikit.ui.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun IntouchButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.titleMedium,
    isEnabled: Boolean = true,
    isHasStroke: Boolean = false,
    enableBackgroundColor: Color = InTouchTheme.colors.mainGreen,
    disableBackgroundColor: Color = InTouchTheme.colors.unableElementLight,
    enableTextColor: Color = InTouchTheme.colors.input,
    disableTextColor: Color = InTouchTheme.colors.mainGreen40,
    borderStrokeColor: Color = InTouchTheme.colors.mainGreen,
    contentPadding: PaddingValues = PaddingValues(horizontal = 64.dp, vertical = 24.dp),
    shape: Shape = RoundedCornerShape(20.dp),
) {
    Button(
        shape = shape,
        modifier = modifier,
        contentPadding = contentPadding,
        border = if (isHasStroke) {
            BorderStroke(1.dp, borderStrokeColor)
        } else {
            BorderStroke(
                0.dp,
                if (isEnabled) enableBackgroundColor else disableBackgroundColor
            )
        },
        enabled = isEnabled,
        colors = ButtonColors(
            containerColor = enableBackgroundColor,
            contentColor = enableTextColor,
            disabledContainerColor = disableBackgroundColor,
            disabledContentColor = disableTextColor,
        ),
        onClick = { onClick.invoke() },
    )
    {
        Text(text = text.value(), style = textStyle)
    }
}

@Composable
fun PrimaryButtonWhite(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.titleMedium,
    isEnabled: Boolean = true,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        isEnabled = isEnabled,
        enableBackgroundColor = InTouchTheme.colors.input,
        disableBackgroundColor = InTouchTheme.colors.input,
        enableTextColor = InTouchTheme.colors.textBlue,
        disableTextColor = InTouchTheme.colors.textBlue
    )
}

@Composable
fun PrimaryButtonGreen(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    isEnabled: Boolean = true,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        isEnabled = isEnabled,
    )
}

@Composable
fun PrimaryButtonStroke(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    isEnabled: Boolean = true,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        isEnabled = isEnabled,
        isHasStroke = true,
        borderStrokeColor = InTouchTheme.colors.textBlue50,
        enableBackgroundColor = InTouchTheme.colors.input,
        disableBackgroundColor = InTouchTheme.colors.input,
        enableTextColor = InTouchTheme.colors.textBlue,
        disableTextColor = InTouchTheme.colors.textBlue
    )
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonGreenPreview() {
    InTouchTheme {
        PrimaryButtonGreen(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Set Password"),
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonWhitePreview() {
    InTouchTheme {
        PrimaryButtonWhite(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Set Password"),
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonStrokePreview() {
    InTouchTheme {
        PrimaryButtonStroke(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Set Password"),
            isEnabled = true
        )
    }
}