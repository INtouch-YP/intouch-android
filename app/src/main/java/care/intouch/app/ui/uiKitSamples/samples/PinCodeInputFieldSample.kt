package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputFieldDefaults

@Composable
fun PinCodeInputFieldSample() {
    var textState by rememberSaveable { mutableStateOf("") }
    var pinCode by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PinCodeInputField(
            value = textState,
            onValueChange = { code ->
                textState = code
                if (code.length == PinCodeInputFieldDefaults.DEFAULT_PIN_CODE_LENGTH)
                    pinCode = code
            },
            isError = isError,
            modifier = Modifier
                .padding(top = 24.dp)
                .focusRequester(focusRequester)
        )
        Text(
            text = pinCode,
            style = InTouchTheme.typography.titleAccent,
            modifier = Modifier.padding(24.dp)
        )

        PrimaryButtonGreen(onClick = {
            textState = BLANC_STRING
            pinCode = BLANC_STRING
        }, modifier = Modifier.padding(bottom = 24.dp), text = "Clear")

        PrimaryButtonGreen(
            onClick = { isError = !isError },
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Error"
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun PinCodeInputFieldSamplePreview() {
    InTouchTheme {
        PinCodeInputFieldSample()
    }
}

const val BLANC_STRING = ""