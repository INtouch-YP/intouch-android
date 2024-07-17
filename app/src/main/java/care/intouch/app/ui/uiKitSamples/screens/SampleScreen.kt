package care.intouch.app.ui.uiKitSamples.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun SampleScreen(
    onButtonSampleClick: () -> Unit,
    onChipSampleClick: () -> Unit,
    onMultilineTextFieldSampleClick: () -> Unit,
    onNavigationSampleClick: () -> Unit,
    onOneLineTextFieldSampleClick: () -> Unit,
    onPasswordTextFieldSampleClick: () -> Unit,
    onSliderSampleClick: () -> Unit,
    onToggleSampleClick: () -> Unit,
    onProgressBarSampleClick: () -> Unit,
    onPinCodeSampleClick: () -> Unit,
    onCheckmarkSampleClick: () -> Unit,
    onCheckboxSampleClick: () -> Unit,
    onCardSampleClick: () -> Unit,
    onButtonSample2Click: () -> Unit,
    onEmotionClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "UI Sample Mode",
            style = InTouchTheme.typography.titleSmall
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onButtonSampleClick.invoke() }
        ) {
            Text(text = "Go To Button Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onChipSampleClick.invoke() }
        ) {
            Text(text = "Go To Chips Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onMultilineTextFieldSampleClick.invoke() }
        ) {
            Text(text = "Go To Multiline Textfield Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onNavigationSampleClick.invoke() }
        ) {
            Text(text = "Go To Navigation Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onOneLineTextFieldSampleClick.invoke() }
        ) {
            Text(text = "Go To One Line Text Field Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onPasswordTextFieldSampleClick.invoke() }
        ) {
            Text(text = "Go To Password Textfield Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSliderSampleClick.invoke() }
        ) {
            Text(text = "Go To Slider Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onToggleSampleClick.invoke() }
        ) {
            Text(text = "Go To Toggle Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onProgressBarSampleClick.invoke() }
        ) {
            Text(text = "Go To Progress Bar Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onPinCodeSampleClick.invoke() }
        ) {
            Text(text = "Go To Pin Code Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCheckmarkSampleClick.invoke() }
        ) {
            Text(text = "Go To Checkmark Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCheckboxSampleClick.invoke() }
        ) {
            Text(text = "Go To Checkbox Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCardSampleClick.invoke() }
        ) {
            Text(text = "Go To Card Sample")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onButtonSample2Click.invoke() }
        ) {
            Text(text = "Go To Button 2 Sample")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SampleScreenPreview() {
    InTouchTheme {
        SampleScreen(
            onButtonSampleClick = {},
            onChipSampleClick = {},
            onMultilineTextFieldSampleClick = {},
            onNavigationSampleClick = {},
            onOneLineTextFieldSampleClick = {},
            onPasswordTextFieldSampleClick = {},
            onSliderSampleClick = {},
            onToggleSampleClick = {},
            onProgressBarSampleClick = {},
            onPinCodeSampleClick = {},
            onCheckmarkSampleClick = {},
            onCheckboxSampleClick = {},
            onCardSampleClick = {},
            onButtonSample2Click = {},
            onEmotionClick = {}
        )
    }
}