package care.intouch.uikit.ui.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun SliderWidgetWithDigits(
    modifier: Modifier = Modifier,
    inactiveTrackColor: Color,
    activeTrackColor: Color,
    internalRadiusThumbColor: Color,
    externalRadiusThumbColor: Color,
    addTopDigitPanel: Boolean = true,
    addBottomConditionsPanel: Boolean = true,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        if (addTopDigitPanel) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end= 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                (1..10).forEach { value ->
                    Text(
                        modifier = Modifier.padding(start = if (value in 2..<10) 4.dp else 0.dp),
                        text = value.toString(),
                        style = InTouchTheme.typography.subTitle,
                        color = InTouchTheme.colors.textBlue
                    )
                }
            }
        }


        CustomSliderWithSteps(
            inactiveTrackColor = inactiveTrackColor,
            activeTrackColor = activeTrackColor,
            internalRadiusThumbColor = internalRadiusThumbColor,
            externalRadiusThumbColor = externalRadiusThumbColor,
            onValueChange = onValueChange
        )

        if (addBottomConditionsPanel) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dissatisfied",
                    modifier = Modifier.weight(1f),
                    style = InTouchTheme.typography.bodyRegular,
                    color = InTouchTheme.colors.textBlue
                )
                Text(
                    text = "Satisfied",
                    style = InTouchTheme.typography.bodyRegular,
                    color = InTouchTheme.colors.textBlue
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SliderWidgetWithDigitsPreview() {
    SliderWidgetWithDigits(
        inactiveTrackColor = InTouchTheme.colors.mainBlue,
        activeTrackColor = InTouchTheme.colors.mainGreen,
        internalRadiusThumbColor = InTouchTheme.colors.mainGreen,
        externalRadiusThumbColor = InTouchTheme.colors.input,
        onValueChange = {},
        addTopDigitPanel = true,
        addBottomConditionsPanel = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSlider(
    inactiveTrackColor: Color,
    activeTrackColor: Color,
    internalRadiusThumbColor: Color,
    externalRadiusThumbColor: Color,
    onValueChange: (Float) -> Unit
) {
    var sliderPosition by remember { mutableFloatStateOf(0.0f) }

    Slider(
        value = sliderPosition,
        onValueChange = { value ->
            sliderPosition = value
            onValueChange.invoke(value)
        },
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
                withSteps = false
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SimpleSliderPreview() {
    InTouchTheme {
        SimpleSlider(
            inactiveTrackColor = InTouchTheme.colors.mainBlue,
            activeTrackColor = InTouchTheme.colors.mainGreen,
            internalRadiusThumbColor = InTouchTheme.colors.mainGreen,
            externalRadiusThumbColor = InTouchTheme.colors.input,
            onValueChange = {}
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
    onValueChange: (Float) -> Unit
) {
    var sliderPosition by remember { mutableFloatStateOf(1.0f) }

    Slider(
        value = sliderPosition,
        onValueChange = { value ->
            sliderPosition = value
            onValueChange.invoke(value)
        },
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
                withSteps = true
            )
        },
        steps = 8,
        valueRange = 1.0f..10.0f,
    )
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
            onValueChange = {}
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
    withSteps: Boolean,
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
                    fraction = if (withSteps)
                        ((sliderState.value - 1) * (1.0 / (10 - 1))).toFloat()
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
            withSteps = true
        )
    }
}