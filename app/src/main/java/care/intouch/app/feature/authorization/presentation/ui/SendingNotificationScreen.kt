package care.intouch.app.feature.authorization.presentation.ui

import androidx.compose.foundation.layout.Box
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
fun SendingNotificationScreen(
    onGoBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp),
            text = "SendingNotificationScreen",
            style = InTouchTheme.typography.titleMedium
        )

        Button(
            onClick = {
                onGoBackClick.invoke()
            }
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SendingNotificationScreenPreview() {
    InTouchTheme {
        SendingNotificationScreen(
            onGoBackClick = {}
        )
    }
}