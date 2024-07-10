package care.intouch.uikit.ui.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun DiaryHeader(
    modifier: Modifier = Modifier,
    title: StringVO,
    backgroundImage: ImageVO = ImageVO.Resource(R.drawable.head_background_small),
    onBackArrowClick: () -> Unit,
    titleStyle: TextStyle = InTouchTheme.typography.titleLarge,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.height(118.dp),
            contentScale = ContentScale.FillBounds,
            painter = backgroundImage.painter(),
            contentDescription = null
        )
        CustomTopBar(
            title = title.value(),
            titleStyle = titleStyle,
            onBackArrowClick = onBackArrowClick,
            onCloseButtonClick = {},
            enabledArcButton = false,
            addBackArrowButton = true,
            addCloseButton = false,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryHeaderPreview() {
    DiaryHeader(
        title = StringVO.Plain("Diary"),
        onBackArrowClick = {}
    )
}