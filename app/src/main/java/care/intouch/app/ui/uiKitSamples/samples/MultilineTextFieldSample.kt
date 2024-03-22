package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.textFields.MultilineTextField

@Composable
fun MultilineTextFieldSampleScreen() {
    val focusManager = LocalFocusManager.current
    var isError by rememberSaveable { mutableStateOf(false) }
    var isEnable by rememberSaveable { mutableStateOf(true) }
    var isReadOnly by rememberSaveable { mutableStateOf(false) }


    var text by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            MultilineTextField(
                titleText = StringVO.Plain("Text small"),
                subtitleText = StringVO.Plain("Lorem ipsum dolor sit amet consectetur. Pellentesque eget non ipsum libero et. Parturient imperdiet."),
                hint = StringVO.Plain("Write your answer here..."),
                value = text,
                onValueChange = {
                    text = it
                },
                isError = isError,
                enabled = isEnable,
                readOnly = isReadOnly,
                modifier = Modifier
                    .padding(8.dp)
                    .width(334.dp)
            )

            MultilineTextField(
                subtitleText = StringVO.Plain("Text small. Lorem ipsum sit amet consectetur. Pellentesque eget non ipsum et."),
                textPadding = 2.dp,
                value = text,
                onValueChange = {
                    text = it
                },
                hint = StringVO.Plain("Write your answer here..."),
                isError = isError,
                enabled = isEnable,
                readOnly = isReadOnly,
                modifier = Modifier
                    .padding(8.dp)
                    .width(334.dp)
            )

            MultilineTextField(
                subtitleText = StringVO.Plain("What motivates you to pursue your current career path?"),
                captionText = StringVO.Plain("Please share insights into the motivations and factors that inspire your choice of the current professional journey."),
                value = text,
                onValueChange = {
                    text = it
                },
                hint = StringVO.Plain("Write your answer here..."),
                isError = isError,
                enabled = isEnable,
                readOnly = isReadOnly,
                modifier = Modifier
                    .padding(8.dp)
                    .width(334.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                    },
                ) {
                    Text("Submit (Clear focus)")
                }

                Button(
                    onClick = {
                        isError = !isError
                    },
                ) {
                    Text("Error change")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {

                Button(
                    onClick = {
                        isEnable = !isEnable
                    },
                ) {
                    Text("Enable change")
                }

                Button(
                    onClick = {
                        isReadOnly = !isReadOnly
                    },
                ) {
                    Text("Read only change")
                }
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