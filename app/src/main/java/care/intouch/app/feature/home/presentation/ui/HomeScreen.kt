package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.accentYellow),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp),
            text = "HomeScreen",
            style = InTouchTheme.typography.titleMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    InTouchTheme {
        HomeScreen()
    }
}