package care.intouch.uikit.ui.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun DeleteButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.bodyBold,
    isEnabled: Boolean = true,
    isActive: Boolean = false,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        isEnabled = isEnabled,
        contentPadding = PaddingValues(horizontal = 51.5.dp, vertical = 11.5.dp),
        enableBackgroundColor = if (isActive) {
            InTouchTheme.colors.errorMaroonColor
        } else {
            InTouchTheme.colors.errorRed
        },
        disableBackgroundColor = InTouchTheme.colors.errorRed,
        enableTextColor = InTouchTheme.colors.input,
        disableTextColor = InTouchTheme.colors.input
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteButtonDefaultPreview() {
    InTouchTheme {
        DeleteButton(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Delete Profile Forever"),
            isActive = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteButtonActivePreview() {
    InTouchTheme {
        DeleteButton(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Delete Profile Forever"),
            isActive = true
        )
    }
}