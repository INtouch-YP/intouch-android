package care.intouch.app.feature.authorization.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun SetPasswordScreen(
    modifier: Modifier = Modifier,
    userName: String,
    errorPassword: Boolean = false,
    errorPasswordText: String = "",
    onEvent: (AuthorizationEvent) -> Unit
) {
    val context = LocalContext.current
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var isVisiblePassword by rememberSaveable { mutableStateOf(false) }
    var isVisiblePasswordConfirm by rememberSaveable { mutableStateOf(false) }

    var checkmarkAgreementToTerm by remember { mutableStateOf(false) }

    var isEnableButton by remember { mutableStateOf(false) }

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
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                isEnableButton = isEnableButtonCheck(
                    password = password,
                    confirmPassword = confirmPassword,
                    isAgreementToTerm = checkmarkAgreementToTerm
                )
            },
            hint = StringVO.Resource(R.string.password_hint),
            error = errorPassword,
            isPasswordVisible = isVisiblePassword,
            isPasswordVisibleIconVisible = true,
            onPasswordVisibleIconClick = {
                isVisiblePassword = !isVisiblePassword
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                isEnableButton = isEnableButtonCheck(
                    password = password,
                    confirmPassword = confirmPassword,
                    isAgreementToTerm = checkmarkAgreementToTerm
                )
            },
            hint = StringVO.Resource(R.string.password_confirm_hint),
            error = errorPassword,
            caption = StringVO.Plain(errorPasswordText),
            isPasswordVisible = isVisiblePasswordConfirm,
            isPasswordVisibleIconVisible = true,
            onPasswordVisibleIconClick = {
                isVisiblePasswordConfirm = !isVisiblePasswordConfirm
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val annotatedString = buildTermsAndCondition()
            val uriHandler = LocalUriHandler.current
            Checkmark(
                isChecked = checkmarkAgreementToTerm,
                callbackState = {},
                onChangeState = {
                    checkmarkAgreementToTerm = !it
                    isEnableButton = isEnableButtonCheck(
                        password = password,
                        confirmPassword = confirmPassword,
                        isAgreementToTerm = checkmarkAgreementToTerm
                    )
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
            text = stringResource(id = R.string.set_password_button),
            isEnabled = isEnableButton,
            enableBackgroundColor = InTouchTheme.colors.mainGreen,
            disableBackgroundColor = InTouchTheme.colors.unableElementLight,
            enableTextColor = InTouchTheme.colors.input,
            disableTextColor = InTouchTheme.colors.textGreen40,
            onClick = {
                onEvent.invoke(
                    AuthorizationEvent.OnSetPassword(
                        password, confirmPassword
                    )
                )
            }
        )
    }
}

private fun isEnableButtonCheck(
    password: String,
    confirmPassword: String,
    isAgreementToTerm: Boolean
) = password.isNotBlank() && confirmPassword.isNotBlank() && isAgreementToTerm

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