package care.intouch.app.feature.profile.presentation.ui.security

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.app.feature.profile.presentation.ui.security.models.PasswordValidType
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.DeleteButton
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.textFields.PasswordTextField
import kotlinx.coroutines.delay

@Composable
fun SecuritySetPasswordScreen(
    modifier: Modifier = Modifier,
    currentPasswordValid: PasswordValidType,
    isSuccessUpdate: Boolean? = null,
    isPasswordValid: PasswordValidType = PasswordValidType.CORRECT,
    isConfirmPasswordValid: PasswordValidType = PasswordValidType.CORRECT,
    isEnable: Boolean = false,
    onEvent: (SecurityEvent) -> Unit
) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(
        key1 = confirmPassword
    ) {
        if (confirmPassword.isBlank()) return@LaunchedEffect

        delay(1000)
        onEvent(SecurityEvent.OnSetConfirmPassword(confirmPassword))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.change_password_sub_title),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Start,
            color = InTouchTheme.colors.textGreen
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.isFocused) {
                        onEvent(SecurityEvent.OnSetCurrentPassword(currentPassword))
                    }
                },
            value = currentPassword,
            onValueChange = {
                currentPassword = it
            },
            title = StringVO.Resource(R.string.current_password_hint),
            error = (currentPasswordValid != PasswordValidType.CORRECT),
            caption = if (currentPasswordValid != PasswordValidType.CORRECT) {
                currentPasswordValid.getString()
            } else StringVO.Plain(""),
            captionLinesAmount = 2,
            isPasswordVisible = false,
            isPasswordVisibleIconVisible = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent(SecurityEvent.OnSetCurrentPassword(currentPassword))
                }
            )
        )
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .onFocusChanged {
                    if (!it.isFocused && password.isNotBlank()) {
                        onEvent(SecurityEvent.OnSetPassword(password))
                        onEvent(SecurityEvent.OnSetConfirmPassword(confirmPassword))
                    }
                },
            value = password,
            onValueChange = {
                password = it
            },
            title = StringVO.Resource(R.string.new_password_hint),
            error = (isPasswordValid != PasswordValidType.CORRECT),
            caption = if (isPasswordValid != PasswordValidType.CORRECT) {
                isPasswordValid.getString()
            } else StringVO.Plain(""),
            captionLinesAmount = 2,
            isPasswordVisible = false,
            isPasswordVisibleIconVisible = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent(SecurityEvent.OnSetPassword(password))
                    onEvent(SecurityEvent.OnSetConfirmPassword(confirmPassword))
                }
            )
        )
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            title = StringVO.Resource(R.string.confirm_password_hint),
            error = (isConfirmPasswordValid != PasswordValidType.CORRECT),
            caption = if (isConfirmPasswordValid != PasswordValidType.CORRECT) {
                isConfirmPasswordValid.getString()
            } else StringVO.Plain(""),
            captionLinesAmount = 2,
            isPasswordVisible = false,
            isPasswordVisibleIconVisible = true,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = getTextByUpdate(isSuccessUpdate = isSuccessUpdate),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2,
            color = if (isSuccessUpdate == true) InTouchTheme.colors.textGreen else InTouchTheme.colors.errorRed
        )
        Spacer(modifier = Modifier.height(12.dp))
        IntouchButton(
            modifier = Modifier
                .width(176.dp)
                .align(Alignment.CenterHorizontally),
            text = StringVO.Resource(R.string.save_button),
            isEnabled = isEnable,
            enableBackgroundColor = InTouchTheme.colors.mainGreen,
            disableBackgroundColor = InTouchTheme.colors.unableElementLight,
            enableTextColor = InTouchTheme.colors.input,
            disableTextColor = InTouchTheme.colors.textGreen40,
            onClick = {
                onEvent.invoke(
                    SecurityEvent.OnSavePassword(currentPassword, password, confirmPassword)
                )
            }
        )
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.danger_zone),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )
        Spacer(modifier = Modifier.height(16.dp))
        DeleteButton(
            onClick = {
                onEvent.invoke(SecurityEvent.OnCallFormForDelete)
            },
            modifier = Modifier.fillMaxWidth(),
            text = StringVO.Resource(R.string.delete_profile_button)
        )
        Spacer(modifier = Modifier.height(44.dp))
    }
}

@Composable
private fun getTextByUpdate(isSuccessUpdate: Boolean?) = when (isSuccessUpdate) {
    true -> {
        stringResource(R.string.info_about_change_password)
    }

    false -> {
        stringResource(R.string.password_update_error)
    }

    else -> {
        ""
    }
}