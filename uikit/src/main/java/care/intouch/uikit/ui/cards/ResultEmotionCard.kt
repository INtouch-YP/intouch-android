package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun ResultEmotionCard(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onTrashClick: () -> Unit,
    emotionIcon: ImageVO = ImageVO.Resource(R.drawable.icon_terrible_small_active),
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

            Image(
                painter = emotionIcon.painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
            )
            Image(
                painter = ImageVO.Resource(R.drawable.icon_edit).painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 40.dp)
                    .clickable(onClick = onEditClick)
            )
            Image(
                painter = ImageVO.Resource(R.drawable.icon_trash).painter(),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = onTrashClick)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun ResultEmotionCardPreview() {
    InTouchTheme {
        ResultEmotionCard(
            modifier = Modifier.padding(45.dp),
            onEditClick = {},
            onTrashClick = {}
        )
    }
}