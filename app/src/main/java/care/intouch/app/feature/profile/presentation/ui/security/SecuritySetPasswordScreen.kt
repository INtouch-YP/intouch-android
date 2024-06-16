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
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.DeleteButton
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.textFields.PasswordTextField
import java.util.regex.Pattern

@Composable
fun SecuritySetPasswordScreen(
    modifier: Modifier = Modifier,
    errorPassword: PasswordInvalidType,
    isSuccessUpdate: Boolean? = null,
    isEnabled: Boolean = false,
    onEvent: (SecurityEvent) -> Unit
) {

    var currentPassword by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var isVisibleCurrentPassword by rememberSaveable { mutableStateOf(false) }
    var isVisiblePassword by rememberSaveable { mutableStateOf(false) }
    var isVisiblePasswordConfirm by rememberSaveable { mutableStateOf(false) }

    var isPasswordValid by rememberSaveable { mutableStateOf(PasswordInvalidType.CORRECT) }
    var isPasswordConfirmValid by rememberSaveable { mutableStateOf(PasswordInvalidType.CORRECT) }

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
                        onEvent.invoke(SecurityEvent.OnVerifyCurrentPassword(currentPassword))
                    }
                },
            value = currentPassword,
            onValueChange = {
                currentPassword = it
            },
            title = StringVO.Resource(R.string.current_password_hint),
            error = (errorPassword != PasswordInvalidType.CORRECT),
            caption = StringVO.Plain(
                if (errorPassword != PasswordInvalidType.CORRECT) {
                    stringResource(id = errorPassword.getString())
                } else ""
            ),
            captionLinesAmount = 2,
            isPasswordVisible = isVisibleCurrentPassword,
            isPasswordVisibleIconVisible = true,
            onPasswordVisibleIconClick = {
                isVisibleCurrentPassword = !isVisibleCurrentPassword
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent.invoke(SecurityEvent.OnVerifyCurrentPassword(currentPassword))
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.isFocused && password.isNotBlank()) {
                        isPasswordValid = isValidPasswordFormat(password)
                    } else if (password.isBlank()) {
                        isPasswordValid = PasswordInvalidType.CORRECT
                    }
                },
            value = password,
            onValueChange = {
                password = it
            },
            title = StringVO.Resource(R.string.new_password_hint),
            error = (isPasswordValid != PasswordInvalidType.CORRECT),
            caption = StringVO.Plain(
                if (isPasswordValid != PasswordInvalidType.CORRECT) {
                    stringResource(id = isPasswordValid.getString())
                } else ""
            ),
            captionLinesAmount = 2,
            isPasswordVisible = isVisiblePassword,
            isPasswordVisibleIconVisible = true,
            onPasswordVisibleIconClick = {
                isVisiblePassword = !isVisiblePassword
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    isPasswordValid = if (password.isNotBlank()) {
                        isValidPasswordFormat(password)
                    } else {
                        PasswordInvalidType.CORRECT
                    }
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.isFocused) {
                        isPasswordConfirmValid = when {
                            password.isBlank() || confirmPassword.isBlank() -> PasswordInvalidType.CORRECT
                            password == confirmPassword -> PasswordInvalidType.CORRECT
                            else -> PasswordInvalidType.NOT_MATCH
                        }
                    }
                },
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            title = StringVO.Resource(R.string.confirm_password_hint),
            error = (isPasswordConfirmValid != PasswordInvalidType.CORRECT),
            caption = StringVO.Plain(
                if (isPasswordConfirmValid != PasswordInvalidType.CORRECT) {
                    stringResource(id = isPasswordConfirmValid.getString())
                } else ""
            ),
            captionLinesAmount = 2,
            isPasswordVisible = isVisiblePasswordConfirm,
            isPasswordVisibleIconVisible = true,
            onPasswordVisibleIconClick = {
                isVisiblePasswordConfirm = !isVisiblePasswordConfirm
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    isPasswordConfirmValid = when {
                        password.isBlank() || confirmPassword.isBlank() -> PasswordInvalidType.CORRECT
                        password == confirmPassword -> PasswordInvalidType.NOT_MATCH
                        else -> PasswordInvalidType.CORRECT
                    }
                }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = getTextByUpdate(isSuccessUpdate = isSuccessUpdate),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2,
            color = InTouchTheme.colors.textGreen
        )
        Spacer(modifier = Modifier.height(12.dp))
        IntouchButton(
            modifier = Modifier
                .width(176.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.save_button),
            isEnabled = isEnabled,
            enableBackgroundColor = InTouchTheme.colors.mainGreen,
            disableBackgroundColor = InTouchTheme.colors.unableElementLight,
            enableTextColor = InTouchTheme.colors.input,
            disableTextColor = InTouchTheme.colors.textGreen40,
            onClick = {
                onEvent.invoke(
                    SecurityEvent.OnSavePassword(password, confirmPassword)
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
            text = stringResource(id = R.string.delete_profile_button)
        )
        Spacer(modifier = Modifier.height(44.dp))
    }
}

fun isValidPasswordFormat(password: String): PasswordInvalidType {
    val smallPattern = "^.{8,}$"
    val bigPattern = "^.{8,128}$"
    val especialSymbol = "^[a-zA-Z\\d~!?@#\$%^&*_+\\-{}()\\[\\]<>\\/\\\\|\"'.,:;]*\$"

    val missingPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$"
    val spacePattern = "^\\S*\$"
    return when {
        !Pattern.compile(spacePattern).matcher(password).matches() -> {
            PasswordInvalidType.EXIST_SPACE
        }

        !Pattern.compile(especialSymbol).matcher(password).matches() -> {
            PasswordInvalidType.INVALID_SYMBOL
        }

        !Pattern.compile(smallPattern).matcher(password).matches() -> {
            PasswordInvalidType.SMALL_PASSWORD
        }

        !Pattern.compile(bigPattern).matcher(password).matches() -> {
            PasswordInvalidType.BIG_PASSWORD
        }

        !Pattern.compile(missingPattern).matcher(password).matches() -> {
            PasswordInvalidType.MISSING_SYMBOL
        }

        else -> {
            PasswordInvalidType.CORRECT
        }
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
