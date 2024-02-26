package care.intouch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.ui.theme.UiKitSample
import care.intouch.uikit.theme.InTouchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InTouchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = InTouchTheme.colors.mainColorBlue
                ) {
                    var movedUiKitSample by remember {
                        mutableStateOf(false)
                    }
                    if(movedUiKitSample) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            UiKitSample()
                            UikitSampleButton(
                                text = "MainScreen",
                                onClick = { movedUiKitSample = !movedUiKitSample }
                            )
                        }
                    } else {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Greeting("Android")
                            UikitSampleButton(
                                text = "UiKitSample",
                                onClick = { movedUiKitSample = !movedUiKitSample }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        style = InTouchTheme.typography.bodyRegularTypography,
        modifier = modifier
    )
}

@Composable
fun UikitSampleButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonColors(
            containerColor = InTouchTheme.colors.mainColorGreen,
            contentColor = InTouchTheme.colors.inputColor,
            disabledContainerColor = InTouchTheme.colors.mainColorGreen,
            disabledContentColor = InTouchTheme.colors.mainColorGreen,
        ),
        onClick = { onClick.invoke() }
    ) {
        Text(
            text = text,
            style = InTouchTheme.typography.bodyRegularTypography,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InTouchTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = InTouchTheme.colors.mainColorBlue,
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Greeting("Android")
            UikitSampleButton (
                text = "UiKitSample",
                onClick = {}
            )
        }
    }
}