package care.intouch.app.feature.home.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun SegmentHeadLine(
    titleText: String,
    isSubTextEnabled: Boolean,
    seeAllClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titleText,
            style = InTouchTheme.typography.titleMedium,
            color = InTouchTheme.colors.textBlue
        )
        AnimatedVisibility(visible = isSubTextEnabled) {
            Text(
                text = stringResource(id = R.string.see_all),
                style = InTouchTheme.typography.subTitle,
                color = InTouchTheme.colors.textBlue,
                modifier = Modifier
                    .alpha(0.5F)
                    .clickable(enabled = isSubTextEnabled) {
                        seeAllClicked()
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SegmentHeadLinePreview() {
    InTouchTheme {
        SegmentHeadLine(
            titleText = buildString {
                append("Segment Title")
            },
            isSubTextEnabled = true,
            seeAllClicked = {}
        )
    }
}