package care.intouch.uikit.ui.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun ProfileInfoTextField(
    modifier: Modifier = Modifier,
    naming: String,
    value: String,
    icon: ImageVO = ImageVO.Resource(R.drawable.icon_edit),
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = InTouchTheme.colors.transparent,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .background(color = backgroundColor)
    ) {
        Text(
            text = naming,
            style = InTouchTheme.typography.bodySemibold.copy(
                color = InTouchTheme.colors.textGreen
            ),
            modifier = Modifier.padding(bottom = 3.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            interactionSource = interactionSource,
            enabled = enabled,
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
                .padding(bottom = 3.dp, start = 107.dp, end = 30.dp)
        )
        Icon(
            painter = icon.painter(),
            contentDescription = null,
            tint = InTouchTheme.colors.mainGreen,
            modifier = Modifier.align(Alignment.CenterEnd),

            )
    }
}

@Preview
@Composable
fun NameProfileInfoTextFieldPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("Benjamin") }
        ProfileInfoTextField(
            value = text,
            onValueChange = {
                text = it
            },
            enabled = false,
            naming = "Name"
        )
    }
}

@Preview
@Composable
fun SurnameProfileInfoTextFieldPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("Cooper") }
        ProfileInfoTextField(
            value = text,
            onValueChange = {
                text = it
            },
            enabled = false,
            naming = "Last name"
        )
    }
}

@Preview
@Composable
fun MailProfileInfoTextFieldPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("benbut@gmail.com") }
        ProfileInfoTextField(
            value = text,
            onValueChange = {
                text = it
            },
            enabled = false,
            naming = "E-mail"
        )
    }
}
