import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.UikitSampleButton
import care.intouch.app.ui.uiKitSamples.ScreenSample
import care.intouch.app.ui.uiKitSamples.samples.MultilineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.NavigationSample
import care.intouch.app.ui.uiKitSamples.samples.OneLineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.SliderSample
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.slider.SliderWidgetWithDigits

@Composable
fun UiKitSample() {

    var screenSample by remember {
        mutableStateOf<ScreenSample>(ScreenSample.MainSampleMenu)
    }

    when (screenSample) {
        ScreenSample.MainSampleMenu -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { /*TODO moved to Custom Title*/ },
                    text = "Custom Title",
                    style = InTouchTheme.typography.bodyRegular,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { /*TODO moved to Custom Button*/ },
                    text = "Custom Button",
                    style = InTouchTheme.typography.bodyRegular,
                    textAlign = TextAlign.Center
                )
                UikitSampleButton(
                    text = "Go to one line text field sample",
                    onClick = { screenSample = ScreenSample.OneLineTexFieldSample }
                )

                UikitSampleButton(
                    text = "Go to multiline text field sample",
                    onClick = { screenSample = ScreenSample.MultilineTexFieldSample }
                )

                UikitSampleButton(
                    text = "Go to navigation sample",
                    onClick = { screenSample = ScreenSample.NavigationSample }
                )

                UikitSampleButton(
                    text = "Go to slider sample",
                    onClick = { screenSample = ScreenSample.SliderSample }
                )
            }
        }

        ScreenSample.OneLineTexFieldSample -> {
            OneLineTextFieldSampleScreen()
        }

        ScreenSample.MultilineTexFieldSample -> {
            MultilineTextFieldSampleScreen()
        }

        ScreenSample.NavigationSample -> {
            NavigationSample()
        }

        ScreenSample.SliderSample -> {
           SliderSample()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiKitSamplePreview() {
    InTouchTheme {
        UiKitSample()
    }
}

