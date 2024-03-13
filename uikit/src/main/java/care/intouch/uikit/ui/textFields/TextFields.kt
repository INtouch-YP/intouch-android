package care.intouch.uikit.ui.textFields

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun OneLineTextInput(
    titleText: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    hint: String,
    isEnabled: (Boolean) = true,
    height: Dp = 53.dp,
    cornerShape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = Color.White,
    onTextChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester = remember {
        FocusRequester()
    }

    Column {
        Text(
            text = titleText,
            style = InTouchTheme.typography.titleSmallTypography,
            color = InTouchTheme.colors.textColorGreen,
            modifier = modifier.padding(bottom = 8.dp)
        )
        Surface(shape = cornerShape) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color =
                        if (isError) {
                            Color.Red
                        } else if (isFocused) {
                            InTouchTheme.colors.accentColorGreen
                        } else backgroundColor,
                        shape = RoundedCornerShape(12.dp),
                    ),
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BasicTextField(
                        value = text,
                        onValueChange = {
                            text = it
                            onTextChange(it.text)
                        },
                        interactionSource = interactionSource,
                        enabled = isEnabled,
                        cursorBrush = SolidColor(InTouchTheme.colors.textColorGreen),
                        decorationBox = { innerTextField ->
                            if (text.text.isEmpty()) {
                                Text(
                                    text = hint,
                                    style = InTouchTheme.typography.bodyRegularTypography.copy(
                                        color = InTouchTheme.colors.textColorBlue50
                                    )
                                )
                            }
                            innerTextField()
                        },
                        textStyle =
                        if (isEnabled) {
                            InTouchTheme.typography.bodyRegularTypography
                        } else {
                            InTouchTheme.typography.bodyRegularTypography.copy(
                                color = InTouchTheme.colors.textColorBlue50
                            )
                        },
                        singleLine = true,
                        modifier = modifier
                            .height(height)
                            .padding(horizontal = 12.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TextInputPreview() {
    InTouchTheme {
        Surface(color = Color.Gray) {
            OneLineTextInput(
                titleText = "Title small",
                isEnabled = true,
                hint = "Text",
                onTextChange = {
                }
            )
        }
    }
}