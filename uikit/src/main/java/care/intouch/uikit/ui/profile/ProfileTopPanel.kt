package care.intouch.uikit.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun TopPanel(
    text: StringVO,
    icon: ImageVO = ImageVO.Resource(R.drawable.icon_arrow_left),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        Image(
            painter = icon.painter(),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = text.value(),
            style = InTouchTheme.typography.titleLarge,
            color = InTouchTheme.colors.textBlue,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x80338C8B)
@Composable
fun TopPanelPreview() {
    InTouchTheme { TopPanel(text = StringVO.Plain("Profile")) }
}