package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun ToggleSampleScreen() {
    var isChecked by remember { mutableStateOf(true) }

    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Toggle(
                isActive = isChecked,
                isChecked = isChecked
            ) {
                isChecked = it
            }
        }
    }
}