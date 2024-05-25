import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.app.UikitSampleButton
import care.intouch.app.ui.uiKitSamples.ScreenSample
import care.intouch.app.ui.uiKitSamples.samples.ButtonSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.CardsSample
import care.intouch.app.ui.uiKitSamples.samples.ButtonSampleScreenSecond
import care.intouch.app.ui.uiKitSamples.samples.CheckboxSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.CheckmarkSample
import care.intouch.app.ui.uiKitSamples.samples.MultilineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.NavigationSample
import care.intouch.app.ui.uiKitSamples.samples.OneLineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.PasswordTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.PinCodeInputFieldSample
import care.intouch.app.ui.uiKitSamples.samples.ProgressBarSample
import care.intouch.app.ui.uiKitSamples.samples.RegularChipsSample
import care.intouch.app.ui.uiKitSamples.samples.SliderSample
import care.intouch.app.ui.uiKitSamples.samples.ToggleSampleScreen
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun UiKitSample() {

    var screenSample by remember {
        mutableStateOf<ScreenSample>(ScreenSample.MainSampleMenu)
    }

    when (screenSample) {
        ScreenSample.MainSampleMenu -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

               UikitSampleButton(
                    text = "Go to custom buttons sample",
                    onClick = { screenSample = ScreenSample.ButtonsSample }
                )

                UikitSampleButton(
                    text = "Go to custom buttons sample 2 ",
                    onClick = { screenSample = ScreenSample.ButtonsSample2 }
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
                    text = "Go to chips sample",
                    onClick = { screenSample = ScreenSample.ChipsSample }
                    )

                UikitSampleButton(
                    text = "Go to toggle sample",
                    onClick = { screenSample = ScreenSample.ToggleSample }
                )

                UikitSampleButton(
                    text = "Go to password text field sample",
                    onClick = { screenSample = ScreenSample.PasswordInputSample }
                )

                UikitSampleButton(
                    text = "Go to slider sample",
                    onClick = { screenSample = ScreenSample.SliderSample }
                )

                UikitSampleButton(
                    text = "Go to checkbox sample",
                    onClick = { screenSample = ScreenSample.CheckboxSample }
                )

                UikitSampleButton(
                    text = "Go to progress bar sample",
                    onClick = { screenSample = ScreenSample.ProgressBarSample }
                )

                UikitSampleButton(
                    text = "Go to checkmark sample",
                    onClick = { screenSample = ScreenSample.CheckmarkSample }
                )
                
                UikitSampleButton(
                    text = "Go to cards sample",
                    onClick = { screenSample = ScreenSample.CardsSample }
                )

                UikitSampleButton(
                    text = "Go to pin code input field sample",
                    onClick = { screenSample = ScreenSample.PinCodeInputFieldSample }
                )
            }
        }

        ScreenSample.ButtonsSample -> {
            ButtonSampleScreen()
        }

        ScreenSample.ButtonsSample2 -> {
            ButtonSampleScreenSecond()
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

        ScreenSample.ChipsSample -> {
            RegularChipsSample()
        }

        ScreenSample.ToggleSample -> {
            ToggleSampleScreen()
        }

        ScreenSample.PasswordInputSample -> {
            PasswordTextFieldSampleScreen()
        }

        ScreenSample.SliderSample -> {
            SliderSample()
        }

        ScreenSample.CheckboxSample -> {
            CheckboxSampleScreen()
        }

        ScreenSample.ProgressBarSample ->{
            ProgressBarSample()
        }

        ScreenSample.CardsSample -> {
            CardsSample()
        }

        ScreenSample.CheckmarkSample -> {
            CheckmarkSample()
        }

        ScreenSample.PinCodeInputFieldSample -> {
            PinCodeInputFieldSample()
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

