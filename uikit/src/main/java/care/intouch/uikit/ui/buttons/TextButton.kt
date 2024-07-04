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
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    isEnabled: Boolean = true,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        isEnabled = isEnabled,
        enableBackgroundColor = InTouchTheme.colors.transparent,
        disableBackgroundColor = InTouchTheme.colors.transparent,
        enableTextColor = InTouchTheme.colors.white,
        disableTextColor = InTouchTheme.colors.unableElementLight,
        contentPadding = PaddingValues(16.dp)
    )
}
@Preview
@Composable
fun TextButtonPreview() {
    InTouchTheme {
        TextButton(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Call to action"),
            isEnabled = true
        )
    }
}