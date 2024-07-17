package care.intouch.app.feature.diary.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun IntouchIconTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: StringVO,
    icon: Painter = ImageVO.Resource(R.drawable.icon_plus_small).painter(),
    isEnabled: Boolean = true,
    isHasStroke: Boolean = false,
    enableBackgroundColor: Color = InTouchTheme.colors.textGreen,
    disableBackgroundColor: Color = InTouchTheme.colors.unableElementLight,
    enableTextColor: Color = InTouchTheme.colors.input,
    disableTextColor: Color = InTouchTheme.colors.mainGreen40,
    borderStrokeColor: Color = InTouchTheme.colors.mainGreen,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            shape = shape,
            modifier = modifier.height(38.dp),
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
            onClick = { onClick.invoke() }
        ) {
            Icon(
                modifier = Modifier.padding(end = 12.dp),
                painter = icon,
                contentDescription = null,
                tint = enableTextColor
            )
            Text(
                text = text.value(),
                style = InTouchTheme.typography.caption1Semibold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IntouchIconTextButtonPreview() {
    InTouchTheme {
        IntouchIconTextButton(
            onClick = { },
            modifier = Modifier,
            icon = ImageVO.Resource(R.drawable.icon_plus_small).painter(),
            text = StringVO.Plain("Add emotions")
        )
    }
}