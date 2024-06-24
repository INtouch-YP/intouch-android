package care.intouch.app.feature.authorization.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.app.core.utils.BLANC_STRING
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.screens.authorization.password_recovery.AuthorizationHeader
import care.intouch.uikit.ui.textFields.PasswordTextField

@Composable
fun PasswordRecoverScreen(
    someArg: String
) {

}

@Composable
fun PasswordRecoveryScreen(
    onSendPasswordClick: () -> Unit,
) {
    var textFieldValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize().background(InTouchTheme.colors.input),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthorizationHeader(
            onCloseButtonClick = {}
        )

        Text(
            modifier = Modifier.padding(top = 71.dp),
            text = StringVO.Resource(R.string.welcome_to_intouch).value(),
            style = InTouchTheme.typography.titleLarge,
            color = InTouchTheme.colors.textGreen
        )

        Text(
            modifier = Modifier.padding(top = 59.dp),
            text = StringVO.Resource(R.string.enter_email_password_recover).value(),
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen
        )

        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 28.dp, end = 28.dp),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            isPasswordVisible = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            error = false,
            caption = StringVO.Plain(BLANC_STRING),
            isPasswordVisibleIconVisible = false,
            onPasswordVisibleIconClick = {
            },
            hint = StringVO.Plain(stringResource(care.intouch.app.R.string.e_mail)),
        )

        PrimaryButtonGreen(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                onSendPasswordClick.invoke()
            },
            text = StringVO.Resource(resId = R.string.send_password_uppercase_button).value(),
            isEnabled = true,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordRecoveryScreenPreview() {
    InTouchTheme {
        PasswordRecoveryScreen(
            onSendPasswordClick = {}
        )
    }
}