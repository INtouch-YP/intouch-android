package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.textFields.MultilineTextField
import care.intouch.uikit.ui.utill.StringVO

@Composable
fun MultilineTextFieldSampleScreen() {
    val focusManager = LocalFocusManager.current
    var isError by rememberSaveable { mutableStateOf(false) }
    var isEnable by rememberSaveable { mutableStateOf(true) }
    var isReadOnly by rememberSaveable { mutableStateOf(false) }


    var text by rememberSaveable { mutableStateOf("") }

    Surface(
        color = InTouchTheme.colors.mainColorBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            MultilineTextField(
                titleText = StringVO.Plain("Multiline text field. \nTitle text"),
                subtitleText = StringVO.Plain("Subtitle text"),
                captionText = StringVO.Plain("Caption text"),
                value = text,
                onValueChange = {
                    text = it
                },
                isError = isError,
                enabled = isEnable,
                readOnly = isReadOnly,
                linesAmount = 8,
                modifier = Modifier
                    .padding(16.dp)
                    .width(334.dp)
            )

            Button(
                onClick = {
                    focusManager.clearFocus()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Submit (Clear focus)")
            }

            Button(
                onClick = {
                    isError = !isError
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Error change")
            }

            Button(
                onClick = {
                    isEnable = !isEnable
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Enable change")
            }

            Button(
                onClick = {
                    isReadOnly = !isReadOnly
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Read only change")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultilineTextFieldSampleScreenPreview() {
    InTouchTheme {
        MultilineTextFieldSampleScreen()
    }
}