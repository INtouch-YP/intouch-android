package care.intouch.uikit.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.NoRippleInteractionSource

@Composable
fun ProfileButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVO = ImageVO.Resource(R.drawable.icon_arrow_small_right),
    isEnabled: Boolean = true,
    isActive: Boolean = true,
    enableBackgroundColor: Color = InTouchTheme.colors.input,
    disableBackgroundColor: Color = InTouchTheme.colors.input,
    enableTextColor: Color = InTouchTheme.colors.textBlue,
    disableTextColor: Color = InTouchTheme.colors.textBlue,
    arrowColor: Color = InTouchTheme.colors.mainGreen,
    contentPadding: PaddingValues = PaddingValues(
        top = 9.dp,
        bottom = 12.dp,
    ),
    shape: Shape = RoundedCornerShape(12.dp),
    text: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.titleSmall,
) {
    Button(
        shape = shape,
        interactionSource = NoRippleInteractionSource(),
        modifier = modifier,
        contentPadding = contentPadding,
        enabled = isEnabled,
        colors = if (isActive) {
            ButtonColors(
                containerColor = disableBackgroundColor,
                contentColor = disableTextColor,
                disabledContainerColor = enableBackgroundColor,
                disabledContentColor = enableTextColor,
            )
        } else {
            ButtonColors(
                containerColor = enableBackgroundColor,
                contentColor = enableTextColor,
                disabledContainerColor = disableBackgroundColor,
                disabledContentColor = disableTextColor,
            )
        },
        onClick = { onClick() }
    )
    {
        Text(text = text.value(), style = textStyle)
        Box(modifier = Modifier.weight(1f))
        Icon(
            painter = icon.painter(),
            contentDescription = null,
            tint = arrowColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x80338C8B)
@Composable
fun ProfileButtonPreview() {
    InTouchTheme {
        ProfileButton(
            text = StringVO.Plain("Security"),
            modifier = Modifier,
            onClick = {})
    }
}