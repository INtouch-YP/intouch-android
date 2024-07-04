package care.intouch.uikit.ui.screens.authorization.password_recovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun AuthorizationHeader(
    isVisibleTopBar: Boolean = true,
    onCloseButtonClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = ImageVO.Resource(res = R.drawable.head_background_small).painter(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier.padding(top = 88.dp),
            painter = ImageVO.Resource(res = R.drawable.head_logo).painter(),
            contentDescription = null,
        )
        if (isVisibleTopBar) {
            Box(
                modifier = Modifier
                    .padding(top = 56.dp)
            ) {
                CustomTopBar(
                    onBackArrowClick = {},
                    onCloseButtonClick = {
                        onCloseButtonClick.invoke()
                    },
                    title = "",
                    enabledArcButton = false,
                    addCloseButton = true,
                    addBackArrowButton = false
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AuthorizationHeaderPreview() {
    AuthorizationHeader(
        onCloseButtonClick = {}
    )
}