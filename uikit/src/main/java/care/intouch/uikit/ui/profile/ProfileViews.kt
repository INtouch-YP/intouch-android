package care.intouch.uikit.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.NoRippleInteractionSource

@Composable
fun TopPanel(
    text: StringVO,
    icon: ImageVO = ImageVO.Resource(R.drawable.icon_arrow_left),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(32.dp)
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        Image(
            painter = icon.painter(),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = text.value(),
            style = InTouchTheme.typography.titleLarge,
            color = InTouchTheme.colors.textBlue,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PersonalData(
    modifier: Modifier = Modifier,
    naming: StringVO,
    value: String,
    icon: ImageVO = ImageVO.Resource(R.drawable.icon_edit),
    onValueChange: (String) -> Unit,
    onIconClick:  () -> Unit,
    textFieldEnabled: Boolean,
    readOnly: Boolean = false,
    buttonEnabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = InTouchTheme.colors.transparent,
    iconTint: Color = InTouchTheme.colors.mainGreen,
    focusRequester: FocusRequester = FocusRequester()
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .background(color = backgroundColor)
            .height(24.dp)
    ) {
        Text(
            text = naming.value(),
            style = InTouchTheme.typography.bodySemibold.copy(
                color = InTouchTheme.colors.textGreen
            ),
            modifier = Modifier.padding(vertical = 3.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            interactionSource = interactionSource,
            enabled = textFieldEnabled,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(InTouchTheme.colors.textGreen),
            textStyle = InTouchTheme.typography.bodyRegular.copy(
                color = InTouchTheme.colors.textGreen
            ),
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp, top = 3.dp, start = 104.dp, end = 32.dp)
                .focusRequester(focusRequester)
        )
        IconButton(
            onClick = onIconClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            enabled = buttonEnabled,
            interactionSource = NoRippleInteractionSource(),
        ) {
            Icon(
                painter = icon.painter(),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

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

@Composable
fun RowWithMessage(
    successOrError: Boolean,
    messageText: StringVO,
    textStyle: TextStyle = InTouchTheme.typography.caption1Regular,
    colorError: Color = InTouchTheme.colors.errorRed,
    colorSuccess: Color = InTouchTheme.colors.green,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(32.dp)
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = messageText.value(),
            style = textStyle,
            color = if (successOrError) {
                colorSuccess
            } else {
                colorError
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x80338C8B)
@Composable
fun TopPanelPreview() {
    InTouchTheme { TopPanel(text = StringVO.Plain("Profile")) }
}

@Preview
@Composable
fun NameProfileInfoTextFieldPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("Benjamin") }
        PersonalData(
            value = text,
            onValueChange = {
                text = it
            },
            textFieldEnabled = false,
            naming = StringVO.Plain("Name"),
            onIconClick = {}
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

@Preview(showBackground = true, backgroundColor = 0x80338C8B)
@Composable
fun RowWithMessagePreview() {
    InTouchTheme {
        RowWithMessage(
            successOrError = true,
            messageText = StringVO.Plain("resultOfCheckData.message\nresultOfCheckData.message2"),
        )
    }
}