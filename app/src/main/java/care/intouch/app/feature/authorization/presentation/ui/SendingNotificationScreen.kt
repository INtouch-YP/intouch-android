package care.intouch.app.feature.authorization.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.screens.authorization.password_recovery.AuthorizationHeader

@Composable
fun SendingNotificationScreen(
    onGoBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(InTouchTheme.colors.input),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthorizationHeader(
            isVisibleTopBar = false
        )

        Text(
            modifier = Modifier.padding(top = 72.dp),
            text = StringVO.Resource(R.string.welcome_to_intouch).value(),
            style = InTouchTheme.typography.titleLarge,
            color = InTouchTheme.colors.textGreen
        )

        Text(
            modifier = Modifier.padding(top = 60.dp, start = 28.dp, end = 28.dp),
            text = StringVO.Resource(R.string.if_account_exist).value(),
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen,
            textAlign = TextAlign.Center
        )

        PrimaryButtonGreen(
            modifier = Modifier.padding(top = 48.dp),
            onClick = {
                onGoBackClick.invoke()
            },
            text = StringVO.Resource(resId = R.string.back_button),
            isEnabled = true,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SendingNotificationScreenPreview() {
    InTouchTheme {
        SendingNotificationScreen(
            onGoBackClick = {}
        )
    }
}