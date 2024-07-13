package care.intouch.app.feature.pinCode.ui.change

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen

@Composable
fun SuccessfulPinCodeChangeScreen(onBackToHome: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = ImageVO.Resource(R.drawable.head_background_small).painter(),
                contentDescription = null
            )
            Image(
                painter = ImageVO.Resource(R.drawable.head_logo).painter(),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 88.dp)
                .padding(horizontal = 68.dp),
            text = StringVO.Resource(care.intouch.app.R.string.info_about_change_pin_code).value(),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )
        PrimaryButtonGreen(
            onClick = { onBackToHome() },
            modifier = Modifier.padding(top = 68.dp),
            text = StringVO.Resource(care.intouch.app.R.string.back_to_home_button)
        )
    }
}