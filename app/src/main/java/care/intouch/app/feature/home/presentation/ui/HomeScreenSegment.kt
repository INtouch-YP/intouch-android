package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun HomeScreenSegment(
    modifier: Modifier,
    isSeeAllVisible: Boolean,
    titleText: String,
    seeAllClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SegmentHeadLine(
            titleText = titleText,
            isSubTextEnabled = isSeeAllVisible,
            seeAllClicked = seeAllClicked
        )
        content()
    }
}

@Composable
@Preview(showBackground = true, heightDp = 360)
fun HomeScreenSegmentPreview() {
    InTouchTheme {
        HomeScreenSegment(
            modifier = Modifier,
            isSeeAllVisible = true,
            titleText = buildString {
                append("Title")
            },
            seeAllClicked = {}) {}
    }
}