package care.intouch.app.feature.authorization.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.checkmark.Checkmark
import care.intouch.uikit.ui.textFields.PasswordTextField
import kotlinx.coroutines.delay

@Composable
fun SetPasswordScreen(
    modifier: Modifier = Modifier,
    userName: String,
    isPasswordValid: PasswordValidType = PasswordValidType.CORRECT,
    isConfirmPasswordValid: PasswordValidType = PasswordValidType.CORRECT,
    isEnable: Boolean = false,
    isEnableAgreementToTerm: Boolean = false,
    onEvent: (AuthorizationEvent) -> Unit
) {
    val context = LocalContext.current
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(
        key1 = confirmPassword
    ) {
        if (confirmPassword.isBlank()) return@LaunchedEffect

        delay(1000)
        onEvent(AuthorizationEvent.OnSetConfirmPassword(confirmPassword))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.welcome_with_name_title, userName),
            style = InTouchTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )
        Spacer(modifier = Modifier.height(44.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.set_password_sub_title),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.isFocused && password.isNotBlank()) {
                        onEvent(AuthorizationEvent.OnSetPassword(password))
                        onEvent(AuthorizationEvent.OnSetConfirmPassword(confirmPassword))
                    }
                },
            value = password,
            onValueChange = {
                password = it
            },
            hint = StringVO.Resource(R.string.password_hint),
            error = (isPasswordValid != PasswordValidType.CORRECT),
            caption = if (isPasswordValid != PasswordValidType.CORRECT) {
                isPasswordValid.getString()
            } else StringVO.Plain(""),
            isPasswordVisible = false,
            isPasswordVisibleIconVisible = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent(AuthorizationEvent.OnSetPassword(password))
                    onEvent(AuthorizationEvent.OnSetConfirmPassword(confirmPassword))
                }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.isFocused) {
                        onEvent(AuthorizationEvent.OnSetConfirmPassword(confirmPassword))
                    }
                },
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            hint = StringVO.Resource(R.string.password_confirm_hint),
            error = (isConfirmPasswordValid != PasswordValidType.CORRECT),
            caption = if (isConfirmPasswordValid != PasswordValidType.CORRECT) {
                isConfirmPasswordValid.getString()
            } else StringVO.Plain(""),
            isPasswordVisible = false,
            isPasswordVisibleIconVisible = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val annotatedString = buildTermsAndCondition()
            val uriHandler = LocalUriHandler.current
            Checkmark(
                isChecked = isEnableAgreementToTerm,
                callbackState = {},
                onChangeState = {
                    onEvent(AuthorizationEvent.OnUpdateAgreementToTerm(!isEnableAgreementToTerm))
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            ClickableText(
                text = annotatedString,
                style = InTouchTheme.typography.bodyRegular.copy(
                    color = InTouchTheme.colors.textGreen
                ),
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "terms",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        uriHandler.openUri(context.getString(R.string.terms_and_condition_link))
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
        IntouchButton(
            modifier = Modifier.fillMaxWidth(),
            text = StringVO.Resource(R.string.set_password_button),
            isEnabled = isEnable,
            enableBackgroundColor = InTouchTheme.colors.mainGreen,
            disableBackgroundColor = InTouchTheme.colors.unableElementLight,
            enableTextColor = InTouchTheme.colors.input,
            disableTextColor = InTouchTheme.colors.textGreen40,
            onClick = {
                onEvent.invoke(
                    AuthorizationEvent.OnSavePassword(
                        password, confirmPassword
                    )
                )
            }
        )
    }
}

@Composable
private fun buildTermsAndCondition(): AnnotatedString =
    buildAnnotatedString {
        append(stringResource(id = R.string.agreement_to_terms))
        append(" ")
        pushStringAnnotation(
            tag = "terms",
            annotation = stringResource(id = R.string.terms_and_condition_link)
        )
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(id = R.string.terms_and_condition))
        }
        pop()
    }