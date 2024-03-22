package care.intouch.uikit.ui.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.textFields.PasswordTextFieldDefaults.BLANC_STRING
import care.intouch.uikit.ui.textFields.PasswordTextFieldDefaults.MinWidth

/**
One line text field with the title.

 * Width by default is 280dp, but you can override it by applying Modifier.width().
 * Height based on text size and padding 16dp from both sides of the text.

 * @param modifier the [Modifier] to be applied to this text field.
 * @param value the input text to be shown in the text field.
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param titleText the title text above text field.
 * @param errorText the error text below text field.
 * @param isPasswordVisible applies to the password icon choice and [visualTransformation] applied to the text field
 * @param onPasswordVisibleIconClick the callback that is triggered when the password icon is clicked.
 * @param hint the hint text to be shown in the text field, visible when the text field is empty.
 * @param error indicates if the text field's current value is in error. If set to true, the
 * border color will be red, error text appears(if it's not blank).
 * If [enabled] set false - [error] is false (whatever it set here).
 * @param enabled controls the enabled state of this text field. When `false`, this component will
 * not respond to user input, and it will appear visually disabled and disabled to accessibility
 * services.
 * @param readOnly controls the editable state of the text field. When `true`, the text field cannot
 * be modified. However, a user can focus it and copy text from it. Read-only text fields are
 * usually used to display pre-filled forms that a user cannot edit.
 * !Do not change [readOnly] when text is selected (or text field focused), as this cause crash.
 * Known issue by compose developers, may fix in future versions.
 * @param inputTextColorEnabled The color of the input text when the field is [enabled].
 * @param inputTextColorDisabled The color of the input text when the field is not [enabled].
 * @param hintTextColor The color of the hint text.
 * @param titleTextColor The color of the title text.
 * @param errorTextColor The color of the error text.
 * @param inputTextStyle The style of the input text.
 * @param titleTextStyle The style of the title text.
 * @param errorTextStyle The style of the error text.
 * @param borderStrokeErrorColor The color of the border when an error occurs.
 * @param borderStrokeOutFocusColor The color of the border when the field is out of focus.
 * @param borderStrokeOnFocusColor The color of the border when the field is in focus.
 * @param passwordVisibleIcon The icon to show when the password is visible.
 * @param passwordNotVisibleIcon The icon to show when the password is not visible.
 * @param passwordIconTint The tint color for the password icons.
 * @param titleTextPadding The padding around the title text.
 * @param errorTextPadding The padding around the error text.
 * @param visualTransformation transforms the visual representation of the input [value]
 * For example, you can use
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.PasswordVisualTransformation] to
 * create a password text field. By default, no visual transformation is applied.
 * @param keyboardOptions software keyboard options that cosntains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param backgroundColor that will be used for this text field background color.
 * InputColor85 by default.
 **/


@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    titleText: StringVO = StringVO.Plain(BLANC_STRING),
    errorText: StringVO = StringVO.Plain(BLANC_STRING),
    isPasswordVisible: Boolean,
    onPasswordVisibleIconClick: () -> Unit,
    hint: StringVO = StringVO.Plain(BLANC_STRING),
    error: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    inputTextColorEnabled: Color = InTouchTheme.colors.textBlue,
    inputTextColorDisabled: Color = InTouchTheme.colors.textBlue50,
    hintTextColor: Color = InTouchTheme.colors.textBlue50,
    titleTextColor: Color = InTouchTheme.colors.textGreen,
    errorTextColor: Color = InTouchTheme.colors.errorRed,
    inputTextStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    titleTextStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    errorTextStyle: TextStyle = InTouchTheme.typography.caption1Regular,
    borderStrokeErrorColor: Color = Color.Red,
    borderStrokeOutFocusColor: Color = InTouchTheme.colors.textGreen40,
    borderStrokeOnFocusColor: Color = InTouchTheme.colors.textGreen,
    passwordVisibleIcon: ImageVO = ImageVO.Resource(R.drawable.icon_view_on),
    passwordNotVisibleIcon: ImageVO = ImageVO.Resource(R.drawable.icon_view_off),
    passwordIconTint: Color = InTouchTheme.colors.mainGreen,
    titleTextPadding: Dp = 6.dp,
    errorTextPadding: Dp = 4.dp,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier.width(MinWidth)
    ) {

        if (titleText.value().isNotBlank()) {
            Text(
                text = titleText.value(),
                style = titleTextStyle,
                color = titleTextColor,
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = titleTextPadding)
            )
        }
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        error -> borderStrokeErrorColor
                        isFocused -> borderStrokeOnFocusColor
                        else -> borderStrokeOutFocusColor
                    },
                    shape = RoundedCornerShape(10.dp),
                ),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    interactionSource = interactionSource,
                    enabled = enabled,
                    readOnly = readOnly,
                    visualTransformation = if (isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        visualTransformation
                    },
                    cursorBrush = SolidColor(inputTextColorEnabled),
                    decorationBox = { innerTextField ->
                        if (value.isEmpty()) {
                            Text(
                                text = hint.value(),
                                maxLines = 1,
                                style = inputTextStyle.copy(
                                    color = hintTextColor
                                )
                            )
                        }
                        innerTextField()
                    },
                    textStyle = if (enabled) {
                        inputTextStyle.copy(
                            color = inputTextColorEnabled
                        )
                    } else {
                        inputTextStyle.copy(
                            color = inputTextColorDisabled
                        )
                    },
                    singleLine = true,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp, end = 12.dp, top = 14.dp, bottom = 9.dp)
                )
                Icon(
                    painter = if (isPasswordVisible) passwordVisibleIcon.painter() else passwordNotVisibleIcon.painter(),
                    contentDescription = if (isPasswordVisible) stringResource(id = R.string.show_password) else stringResource(
                        id = R.string.hide_password
                    ),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            onPasswordVisibleIconClick.invoke()
                        },
                    tint = passwordIconTint
                )
            }
        }
        if (errorText.value().isNotBlank() && error) {
            Text(
                text = errorText.value(),
                style = errorTextStyle,
                color = errorTextColor,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = errorTextPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        PasswordTextField(
            titleText = StringVO.Plain("Current password"),
            errorText = StringVO.Plain("Passwords must be at least 8 characters long"),
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibleIconClick = { isPasswordVisible = !isPasswordVisible },
            value = text,
            onValueChange = {
                text = it
            },
            hint = StringVO.Plain("Enter password"),
            error = false,
            modifier = Modifier.padding(45.dp)
        )
    }
}

object PasswordTextFieldDefaults {
    /**
     * The default min width applied to a [PasswordTextField].
     * Note that you can override it by applying Modifier.width directly on [PasswordTextField].
     */
    val MinWidth = 280.dp
    const val BLANC_STRING = ""
}