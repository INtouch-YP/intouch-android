package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.slider.SimpleSlider
import care.intouch.uikit.ui.slider.SliderWidgetWithScaleAndTenSteps

@Composable
fun SliderSampleScreen() {

    var simpleSliderValue by remember {
        mutableFloatStateOf(0.0f)
    }

    var sliderWithStepsValue by remember {
        mutableIntStateOf(1)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.accentYellow),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SliderWidgetWithScaleAndTenSteps(
                addTopDigitPanel = true,
                addBottomConditionsPanel = true,
                leftEvaluateText = "Dissatisfied",
                rightEvaluateText = "Satisfied",
                onValueChange = { value ->
                    sliderWithStepsValue = value
                }
            )

            Text(text = "Value: $sliderWithStepsValue")

            SimpleSlider(
                inactiveTrackColor = InTouchTheme.colors.mainBlue,
                activeTrackColor = InTouchTheme.colors.mainGreen,
                internalRadiusThumbColor = InTouchTheme.colors.mainGreen,
                externalRadiusThumbColor = InTouchTheme.colors.input,
                onValueChange = { value ->
                    simpleSliderValue = value
                }
            )

            Text(text = "Value: $simpleSliderValue")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SliderSamplePreview() {
    InTouchTheme {
        SliderSampleScreen()
    }
}