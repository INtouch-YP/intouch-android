package care.intouch.uikit.ui.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun EmptyDiaryPlaceHolder(
    title: StringVO,
    text: StringVO,
) {
    val arrowImage: ImageVO = ImageVO.Resource(R.drawable.icon_arrow_pointer_on_plus)
    Box(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.padding(bottom = 0.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = title.value(),
                style = InTouchTheme.typography.titleMedium,
                color = InTouchTheme.colors.textGreen,
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = InTouchTheme.colors.mainBlue, shape = RoundedCornerShape(20.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.wrapContentSize()
                ) {

                    Text(
                        modifier = Modifier.padding(horizontal = 34.dp, vertical = 22.dp),
                        text = text.value(),
                        style = InTouchTheme.typography.bodySemibold,
                        color = InTouchTheme.colors.textGreen
                    )
                }
            }
            Image(
                modifier = Modifier
                    .padding(start = 86.dp, top = 10.dp)
                    .offset(0.dp, (-20).dp),
                alignment = Alignment.BottomStart,
                painter = arrowImage.painter(),
                contentDescription = "the arrow that points on the creation icon"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyDiaryPlaceHolderPreview() {
    EmptyDiaryPlaceHolder(
        title = StringVO.Plain("My Diary"),
        text = StringVO.Plain("Share your thoughts and feelings — a wonderful way to begin your path to well-being")
    )
}