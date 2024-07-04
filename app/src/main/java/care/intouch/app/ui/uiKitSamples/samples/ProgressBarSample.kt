package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.ProgressBar
import care.intouch.uikit.ui.buttons.IntouchButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressBarSample() {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        ProgressBar()
        ProgressBar(progress = { 0f })
        ProgressBar(progress = { 0.2f })
        ProgressBar(progress = { 0.4f })
        ProgressBar(progress = { 0.6f })
        ProgressBar(progress = { 0.8f })

        if (loading) {
            ProgressBar(
                progress = { currentProgress },
            )
        }

        IntouchButton(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false
            }
        }, isEnabled = !loading, text = StringVO.Plain("Start loading"), modifier = Modifier)
    }

}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIndicator() {
    InTouchTheme {
        ProgressBarSample()
    }
}