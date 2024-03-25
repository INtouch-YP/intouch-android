package care.intouch.uikit.ui.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    inactiveTrackColor: Color,
    activeTrackColor: Color,
    internalRadiusThumbColor: Color,
    externalRadiusThumbColor: Color,
) {
    var sliderPosition by remember { mutableFloatStateOf(0.0f) }

    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            thumb = {
                CustomThumb(
                    internalRadiusThumbColor = internalRadiusThumbColor,
                    externalRadiusThumbColor = externalRadiusThumbColor
                )
            },
            track = { sliderState ->
                CustomTrack(
                    sliderState = sliderState,
                    trackColor = inactiveTrackColor,
                    progressColor = activeTrackColor,
                    height = 8.dp,
                )
            }
        )

        Text(text = sliderPosition.toString())
    }
}

@Composable
@Preview(showBackground = true)
fun CustomSliderPreview() {
    InTouchTheme {
        CustomSlider(
            inactiveTrackColor = InTouchTheme.colors.mainBlue,
            activeTrackColor = InTouchTheme.colors.mainGreen,
            internalRadiusThumbColor = InTouchTheme.colors.mainGreen,
            externalRadiusThumbColor = InTouchTheme.colors.input,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSliderWithSteps(
    inactiveTrackColor: Color,
    activeTrackColor: Color,
    internalRadiusThumbColor: Color,
    externalRadiusThumbColor: Color,
    maxValue: Int
) {
    var sliderPosition by remember { mutableFloatStateOf(1.0f) }

    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            thumb = {
                CustomThumb(
                    internalRadiusThumbColor = internalRadiusThumbColor,
                    externalRadiusThumbColor = externalRadiusThumbColor
                )
            },
            track = { sliderState ->
                CustomTrack(
                    sliderState = sliderState,
                    trackColor = inactiveTrackColor,
                    progressColor = activeTrackColor,
                    height = 8.dp,
                    maxValue = maxValue
                )
            },
            steps = maxValue - 2,
            valueRange = 1.0f..maxValue.toFloat(),
        )
        Text(text = sliderPosition.roundToInt().toString())
    }
}

@Composable
@Preview(showBackground = true)
fun CustomSliderWithStepsPreview() {
    InTouchTheme {
        CustomSliderWithSteps(
            inactiveTrackColor = InTouchTheme.colors.mainBlue,
            activeTrackColor = InTouchTheme.colors.mainGreen,
            internalRadiusThumbColor = InTouchTheme.colors.mainGreen,
            externalRadiusThumbColor = InTouchTheme.colors.input,
            maxValue = 10
        )
    }
}

@Composable
fun CustomThumb(
    internalRadiusThumbColor: Color,
    externalRadiusThumbColor: Color,
) {
    Canvas(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(24.dp)
    ) {
        drawCircle(
            color = externalRadiusThumbColor,
            radius = size.width / 2
        )

        scale(scale = 0.5f) {
            drawCircle(
                color = internalRadiusThumbColor,
                radius = size.width / 2
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CustomThumbPreview() {
    InTouchTheme {
        Surface(color = InTouchTheme.colors.mainBlue) {
            CustomThumb(
                internalRadiusThumbColor = InTouchTheme.colors.mainGreen,
                externalRadiusThumbColor = InTouchTheme.colors.input
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTrack(
    modifier: Modifier = Modifier,
    sliderState: SliderState,
    trackColor: Color,
    progressColor: Color,
    height: Dp,
    maxValue: Int? = null,
    shape: Shape = CircleShape
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = height)
            .clip(shape)
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(
                    fraction = if (maxValue != null)
                        ((sliderState.value - 1) * (1.0 / (maxValue - 1))).toFloat()
                    else
                        sliderState.value
                )
                .heightIn(min = height)
                .clip(shape)

                .background(progressColor)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun CustomTrackPreview() {
    InTouchTheme {
        CustomTrack(
            sliderState = SliderState(),
            trackColor = InTouchTheme.colors.mainBlue,
            progressColor = InTouchTheme.colors.mainGreen,
            height = 8.dp,
            maxValue = 10
        )
    }
}
