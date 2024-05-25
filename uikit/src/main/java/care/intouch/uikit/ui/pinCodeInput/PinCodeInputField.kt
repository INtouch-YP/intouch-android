package care.intouch.uikit.ui.pinCodeInput

import PinCodeInputCell
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputFieldDefaults.BLANC_STRING
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputFieldDefaults.DEFAULT_CELL_SIZE
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputFieldDefaults.DEFAULT_PIN_CODE_LENGTH
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputFieldDefaults.DEFAULT_ROUNDED_CORNER_RADIUS
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputFieldDefaults.DEFAULT_SPACE_BETWEEN_CELLS

/**
Pin code input field

 * Spacing is 8dp between each cell but can be changed with [spacingBetweenCells].
 *
 * @param [modifier] the [Modifier] to be applied to this pin code input.
 * @param [value] the input text to be shown in the pin code input.
 * @param [onValueChange] the callback that is triggered when the input service updates the text.
 * An updated text, that not exceed [pinCodeLength], will be shown in the text field.
 * @param [isError] indicates if the text field's current value is in error. If set to true, the
 * border color of each cell will be red.
 * @param [pinCodeLength] the length of the pin code (cell count). By default, 4 is applied.
 * @param keyboardOptions software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction]. By default, KeyboardType.NumberPassword and ImeAction.None are applied.
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction]. By default, KeyboardActions.Default is applied.
 * @param [spacingBetweenCells] spacing between each cell. By default, 8dp is applied.
 * @param [cellsTextStyle] text style of each cell. By default, InTouchTheme.typography.titleAccent is applied.
 * @param [cellsTextColor] text color of each cell. By default, InTouchTheme.colors.textGreen is applied.
 * @param [cellsBackgroundEmptyColor] background color of each cell when it is empty. By default, InTouchTheme.colors.unableElementLight is applied.
 * @param [cellsBackgroundFilledColor] background color of each cell when it is not empty. By default, InTouchTheme.colors.mainGreen40 is applied.
 * @param [cellsErrorBorderColor] border color of each cell when it is in error. By default, InTouchTheme.colors.errorRed is applied.
 * @param [cellsSize] size of each cell. By default, 70dp is applied.
 * @param [cellsRoundedCornerShape] rounded corner shape of each cell. By default, RoundedCornerShape(12.dp) is applied.
 */

@Composable
fun PinCodeInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    pinCodeLength: Int = DEFAULT_PIN_CODE_LENGTH,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.NumberPassword,
        imeAction = ImeAction.Done
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    spacingBetweenCells: Dp = DEFAULT_SPACE_BETWEEN_CELLS,
    cellsTextStyle: TextStyle = InTouchTheme.typography.titleAccent,
    cellsTextColor: Color = InTouchTheme.colors.textGreen,
    cellsBackgroundEmptyColor: Color = InTouchTheme.colors.unableElementLight,
    cellsBackgroundFilledColor: Color = InTouchTheme.colors.mainGreen40,
    cellsErrorBorderColor: Color = InTouchTheme.colors.errorRed,
    cellsSize: Dp = DEFAULT_CELL_SIZE,
    cellsRoundedCornerShape: RoundedCornerShape = RoundedCornerShape(DEFAULT_ROUNDED_CORNER_RADIUS)
) {
    val interactionSource = remember { MutableInteractionSource() }
    CompositionLocalProvider(
        LocalTextToolbar provides EmptyTextToolbar //Disabling copy/cut/paste/selectAll
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it.take(pinCodeLength))
            },
            interactionSource = interactionSource,
            decorationBox = {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(spacingBetweenCells),
                ) {
                    repeat(pinCodeLength) { index ->
                        val char =
                            when {
                                index >= value.length -> BLANC_STRING
                                else -> value[index].toString()
                            }
                        PinCodeInputCell(
                            text = char,
                            isError = isError,
                            textColor = cellsTextColor,
                            backgroundEmptyColor = cellsBackgroundEmptyColor,
                            backgroundFilledColor = cellsBackgroundFilledColor,
                            errorBorderColor = cellsErrorBorderColor,
                            textStyle = cellsTextStyle,
                            size = cellsSize,
                            roundedCornerShape = cellsRoundedCornerShape,
                        )
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            modifier = modifier
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PinCodeInputFieldPreview() {
    InTouchTheme {
        var textState by rememberSaveable { mutableStateOf("") }
        PinCodeInputField(
            value = textState,
            onValueChange = { textState = it },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PinCodeInputFieldErrorPreview() {
    InTouchTheme {
        PinCodeInputField(
            value = BLANC_STRING,
            onValueChange = { },
            isError = true,
            modifier = Modifier.padding(horizontal = 43.dp, vertical = 4.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PinCodeInputFieldDesignPreview() {
    InTouchTheme {
        var textState by rememberSaveable { mutableStateOf("") }
        Box(
            modifier = Modifier
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            PinCodeInputField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.padding(horizontal = 43.dp, vertical = 4.dp),
            )
        }
    }
}

object EmptyTextToolbar : TextToolbar {
    override val status: TextToolbarStatus = TextToolbarStatus.Hidden
    override fun hide() {}
    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
    }
}

object PinCodeInputFieldDefaults {
    const val DEFAULT_PIN_CODE_LENGTH = 4
    val DEFAULT_SPACE_BETWEEN_CELLS = 8.dp
    val DEFAULT_CELL_SIZE = 70.dp
    val DEFAULT_ROUNDED_CORNER_RADIUS = 12.dp
    const val BLANC_STRING = ""
}