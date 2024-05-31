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
fun DebugModeScreen(
    onUISamplesButtonClick: () -> Unit,
    onNavigationButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "InTouch App Debug Mode",
            style = InTouchTheme.typography.titleSmall
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onUISamplesButtonClick.invoke() }
        ) {
            Text(text = "Go to UI Sample Screen")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {onNavigationButtonClick.invoke()}
        ) {
            Text(text = "Go to Navigation Screen")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ToolkitScreenPreview() {
    InTouchTheme {
        DebugModeScreen(
            onUISamplesButtonClick = {},
            onNavigationButtonClick = {}
        )
    }
}