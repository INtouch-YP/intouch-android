package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.theme.InTouchTheme


@Composable
fun EmotionCard(
    emotion: ImageVO,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Image(
            painter = emotion.painter(),
            contentDescription = "",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmotionCard() {
    InTouchTheme {
        EmotionCard(
            emotion = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_terrible),
            modifier = Modifier,
        )
    }
}