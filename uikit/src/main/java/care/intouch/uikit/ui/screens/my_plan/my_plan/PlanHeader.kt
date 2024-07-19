package care.intouch.uikit.ui.screens.my_plan.my_plan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun PlanHeader(
    modifier: Modifier = Modifier,
    title: StringVO = StringVO.Resource(R.string.my_plan),
    backgroundImage: ImageVO = ImageVO.Resource(R.drawable.my_plan_header),
    titleStyle: TextStyle = InTouchTheme.typography.titleLarge,
    onBackArrowClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
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
            addCloseButton = false
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderPreview() {
    PlanHeader(
        onBackArrowClick = {}
    )
}