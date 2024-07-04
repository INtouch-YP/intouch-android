package care.intouch.uikit.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.NoRippleInteractionSource

@Composable
fun PersonalData(
    modifier: Modifier = Modifier,
    naming: StringVO,
    value: String,
    enabledIcon: ImageVO = ImageVO.Resource(R.drawable.icon_edit),
    disabledIcon: ImageVO = ImageVO.Resource(R.drawable.icon_edit_light),
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
            .heightIn(min = 24.dp, max = 36.dp)
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
                painter = if (buttonEnabled) enabledIcon.painter() else disabledIcon.painter(),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
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