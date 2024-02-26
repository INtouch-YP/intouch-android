package care.intouch.app.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun UiKitSample() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { /*TODO moved to Custom Title*/ },
            text = "Custom Title",
            style = InTouchTheme.typography.bodyRegularTypography,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { /*TODO moved to Custom Button*/ },
            text = "Custom Button",
            style = InTouchTheme.typography.bodyRegularTypography,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UiKitSamplePreview() {
    UiKitSample()
}