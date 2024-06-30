package care.intouch.uikit.ui.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun SecondaryButtonDark(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.subTitle,
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
        enableTextColor = InTouchTheme.colors.textGreen,
        disableTextColor = InTouchTheme.colors.unableElementDark,
    )
}

@Composable
fun SecondaryButtonWhite(
    onClick: () -> Unit,
    modifier: Modifier,
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.subTitle,
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
        enableTextColor = InTouchTheme.colors.input,
        disableTextColor = InTouchTheme.colors.unableElementDark,
    )
}

@Preview(showBackground = true)
@Composable
fun SecondaryButtonDarkPreview() {
    InTouchTheme {
        SecondaryButtonDark(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Call to action"),
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondaryButtonWhitePreview() {
    InTouchTheme {
        SecondaryButtonWhite(
            onClick = {},
            modifier = Modifier,
            text = StringVO.Plain("Call to action"),
            isEnabled = true
        )
    }
}