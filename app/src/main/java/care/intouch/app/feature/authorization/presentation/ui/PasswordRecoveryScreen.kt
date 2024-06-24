package care.intouch.app.feature.authorization.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.R
import care.intouch.app.core.utils.BLANC_STRING
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryEvent
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.RecoveryMove
import care.intouch.app.feature.authorization.presentation.viewModel.PasswordRecoveryViewModel
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.screens.authorization.password_recovery.AuthorizationHeader
import care.intouch.uikit.ui.textFields.PasswordTextField

@Composable
fun PasswordRecoveryScreen(
    onSendPasswordClick: () -> Unit,
    onCloseButtonClick: () -> Unit
) {
    val viewModel: PasswordRecoveryViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    PasswordRecoveryScreen(
        onSendPasswordClick = onSendPasswordClick,
        onCloseButtonClick = onCloseButtonClick,
        state = state,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun PasswordRecoveryScreen(
    onSendPasswordClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    state: PasswordRecoveryScreenState,
    onEvent: (PasswordRecoveryEvent) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthorizationHeader(
            onCloseButtonClick = {
                onCloseButtonClick.invoke()
            }
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
            value = state.textFieldValue,
            onValueChange = {
                onEvent(PasswordRecoveryEvent.OnTextFieldChange(it))
            },
            isPasswordVisible = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            error = false,
            caption = if (state.isVisibleCaption)
                StringVO.Resource(resId = R.string.email_not_valid_error)
            else StringVO.Plain(text = BLANC_STRING),
            isPasswordVisibleIconVisible = false,
            onPasswordVisibleIconClick = {
            },
            hint = StringVO.Plain(stringResource(care.intouch.app.R.string.e_mail)),
            captionTextColor = InTouchTheme.colors.errorRed
        )

        PrimaryButtonGreen(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                onEvent.invoke(PasswordRecoveryEvent.OnPasswordRecovery(email = state.textFieldValue))
            },
            text = StringVO.Resource(resId = R.string.send_password_uppercase_button).value(),
            isEnabled = state.enableButton,
        )
    }

    when(state.recoveryMove) {
        RecoveryMove.SUCCESS -> onSendPasswordClick.invoke()
        RecoveryMove.USER_NOT_EXIST -> onSendPasswordClick.invoke()
        RecoveryMove.FAILURE -> {
            Toast.makeText(context, state.recoveryErrorMessage, Toast.LENGTH_SHORT).show()
            onEvent(PasswordRecoveryEvent.ResetUiState)
        }
        RecoveryMove.UNDEFINED -> {}
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordRecoveryScreenPreview() {
    InTouchTheme {
        PasswordRecoveryScreen(
            onSendPasswordClick = {},
            onCloseButtonClick = {},
            state = PasswordRecoveryScreenState(),
            onEvent = {}
        )
    }
}