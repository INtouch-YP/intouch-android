package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton

@Composable
fun AddEmotionCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = InTouchTheme.colors.input85,
) {

    Column(
        modifier =
        modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp),
                )
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            IntouchButton(
                onClick = onClick,
                modifier = Modifier,
                text = StringVO.Plain("Add Emotions"),
                enableBackgroundColor = InTouchTheme.colors.textGreen,
                disableBackgroundColor = InTouchTheme.colors.textGreen,
                textStyle = InTouchTheme.typography.caption1Semibold,
                contentPadding = PaddingValues(
                    start = 38.dp,
                    top = 12.dp,
                    bottom = 12.dp,
                    end = 12.dp
                ),
                isEnabled = true
            )
            Image(
                painter = ImageVO.Resource(R.drawable.icon_plus_small_white).painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
            )
            Image(
                painter = ImageVO.Resource(R.drawable.icon_terrible_small).painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 80.dp)
            )
            Image(
                painter = ImageVO.Resource(R.drawable.icon_okey_small).painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
            Image(
                painter = ImageVO.Resource(R.drawable.icon_bad_small).painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 40.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddEmotionCardPreview() {
    InTouchTheme {
        AddEmotionCard(
            modifier = Modifier.padding(45.dp),
            onClick = {}
        )
    }
}