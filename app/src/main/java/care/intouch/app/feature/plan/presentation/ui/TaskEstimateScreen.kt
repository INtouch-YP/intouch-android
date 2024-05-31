package care.intouch.app.feature.plan.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun TaskEstimateScreen(
    onDoneClick: () -> Unit,
    onSkipClick: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp),
            text = "TaskEstimateScreen",
            style = InTouchTheme.typography.titleMedium
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    onDoneClick.invoke()
                }
            ) {
                Text(text = "Done")
            }

            Button(
                onClick = {
                    onSkipClick.invoke()
                }
            ) {
                Text(text = "Skip")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TaskEstimateScreenPreview() {
    InTouchTheme {
        TaskEstimateScreen(
            onDoneClick = {},
            onSkipClick = {}
        )
    }
}