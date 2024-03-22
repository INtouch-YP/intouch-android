package care.intouch.uikit.ui.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.BLANC_STRING
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.DEFAULT_LINE_AMOUNT
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.TextPadding

/**
Multiline text field with the title, subtitle and caption.

 * Width by default is 280dp, but you can override it by applying Modifier.width().
 * Height based on text size and padding 12dp from both sides of the text.
 * 5 lines of text by default, but you can change it by setting [linesAmount].
 * Padding between the text field and the text group [titleText], [subtitleText], [captionText] is
 * 8dp by default. But you can change it by setting [textPadding].

 * @param titleText the title text above [subtitleText], [captionText] and text field.
 * @param subtitleText the subtitle text above [captionText], text field and below [titleText].
 * @param captionText the caption text above text field, below [titleText] and [subtitleText].
 * @param textPadding the padding between the text field and the
 * text group [titleText], [subtitleText], [captionText]. By default, 8dp.
 * @param value the input text to be shown in the text field.
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param hint the hint text to be shown in the text field, visible when the text field is empty.
 * @param isError indicates if the text field's current value is in error. If set to true, the
 * border color will be red. If [enabled] set false - [isError] is false (whatever it set here).
 * @param enabled controls the enabled state of this text field. When `false`, this component will
 * not respond to user input, and it will appear visually disabled and disabled to accessibility
 * services, [titleText], [subtitleText] and [captionText] change color as well.
 * @param readOnly controls the editable state of the text field. When `true`, the text field cannot
 * be modified. However, a user can focus it and copy text from it. Read-only text fields are
 * usually used to display pre-filled forms that a user cannot edit.
 * !Do not change [readOnly] when text is selected (or text field focused), as this cause crash.
 * Known issue by compose developers, may fix in future versions.
 * @param linesAmount the number of lines to be shown in the text field. By default, 5 lines.
 * @param visualTransformation transforms the visual representation of the input [value]
 * For example, you can use
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.PasswordVisualTransformation] to
 * create a password text field. By default, no visual transformation is applied.
 * @param keyboardOptions software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param backgroundColor that will be used for this text field background color.
 * InputColor85 by default.
 */

@Composable
fun MultilineTextField(
    modifier: Modifier = Modifier,
    titleText: StringVO = StringVO.Plain(BLANC_STRING),
    subtitleText: StringVO = StringVO.Plain(BLANC_STRING),
    captionText: StringVO = StringVO.Plain(BLANC_STRING),
    textPadding: Dp = TextPadding,
    value: String,
    onValueChange: (String) -> Unit,
    hint: StringVO = StringVO.Plain(BLANC_STRING),
    isError: Boolean,
    enabled: Boolean,
    readOnly: Boolean = false,
    linesAmount: Int = DEFAULT_LINE_AMOUNT,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier.width(MultilineTextFieldDefaults.MinWidth)
    ) {
        if (titleText.value().isNotBlank()
            || subtitleText.value().isNotBlank()
            || captionText.value().isNotBlank()
        ) {
            Column(modifier = Modifier.padding(bottom = textPadding)) {
                if (titleText.value().isNotBlank()) {
                    Text(
                        text = titleText.value(),
                        style = InTouchTheme.typography.titleSmall,
                        color = if (enabled) {
                            InTouchTheme.colors.textBlue
                        } else {
                            InTouchTheme.colors.textBlue50
                        },
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (subtitleText.value().isNotBlank()) {
                    Text(
                        text = subtitleText.value(),
                        modifier = if (titleText.value().isNotBlank()) Modifier
                            .padding(top = 8.dp) else Modifier,
                        style = InTouchTheme.typography.bodySemibold,
                        color = if (enabled) {
                            InTouchTheme.colors.textGreen
                        } else {
                            InTouchTheme.colors.textGreen40
                        },
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (captionText.value().isNotBlank()) {
                    Text(
                        text = captionText.value(),
                        modifier = if (subtitleText.value().isNotBlank() || titleText.value()
                                .isNotBlank()
                        )
                            Modifier.padding(top = 2.dp) else Modifier,
                        style = InTouchTheme.typography.caption1Regular,
                        color = if (enabled) {
                            InTouchTheme.colors.textGreen
                        } else {
                            InTouchTheme.colors.textGreen40
                        },
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        isError && enabled -> InTouchTheme.colors.errorRed
                        isFocused && enabled -> InTouchTheme.colors.accentGreen
                        else -> backgroundColor
                    },
                    shape = RoundedCornerShape(12.dp),
                ),
        ) {
            BasicTextField(
                minLines = linesAmount,
                maxLines = linesAmount,
                value = value,
                onValueChange = onValueChange,
                interactionSource = interactionSource,
                enabled = enabled,
                readOnly = readOnly,
                visualTransformation = visualTransformation,
                cursorBrush = SolidColor(InTouchTheme.colors.textGreen),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = hint.value(),
                            style = InTouchTheme.typography.bodyRegular.copy(
                                color = InTouchTheme.colors.textBlue50
                            )
                        )
                    }
                    innerTextField()
                },
                textStyle =
                if (enabled) {
                    InTouchTheme.typography.bodyRegular
                } else {
                    InTouchTheme.typography.bodyRegular.copy(
                        color = InTouchTheme.colors.textBlue50
                    )
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun MultilineTextFieldPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("") }
        MultilineTextField(
            titleText = StringVO.Plain("Title small "),
            subtitleText = StringVO.Plain("Body semi bold "),
            captionText = StringVO.Plain("Caption "),
            value = text,
            onValueChange = {
                text = it
            },
            hint = StringVO.Plain("Write your answer here..."),
            isError = false,
            enabled = true,
            modifier = Modifier.padding(45.dp)
        )
    }
}

object MultilineTextFieldDefaults {
    /**
     * The default min width applied to a [MultilineTextField].
     * Note that you can override it by applying Modifier.width directly on [MultilineTextField].
     */
    val MinWidth = 280.dp

    /**
     * The default padding between text field and text group applied to a [MultilineTextField].
     * Note that you can override it by setting textPadding on [MultilineTextField].
     */
    val TextPadding = 8.dp

    /**
     * The default line amount applied to a [MultilineTextField].
     * Note that you can override it by setting linesAmount on [MultilineTextField].
     */
    const val DEFAULT_LINE_AMOUNT = 5
    const val BLANC_STRING = ""
}