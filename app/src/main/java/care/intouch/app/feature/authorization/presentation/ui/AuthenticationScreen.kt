package care.intouch.app.feature.authorization.presentation.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.authorization.presentation.ui.models.AuthScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.AuthenticationDataEvent
import care.intouch.app.feature.authorization.presentation.ui.models.AuthenticationSideEffect
import care.intouch.app.feature.authorization.presentation.ui.viewModel.AuthViewModule
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.snackbar.IntouchSnackbar
import care.intouch.uikit.ui.textFields.PasswordTextField

@Composable
fun AuthenticationScreen(
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: AuthViewModule,
) {
    val state by viewModel.uiState.collectAsState()
    val sideEffect = viewModel.sideEffect
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        viewModel.sideEffect.collect {
            when (it) {
                AuthenticationSideEffect.Login -> onLoginClick()
                is AuthenticationSideEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(
                        message = it.message.value(context)
                    )
                }
            }
        }
    }
    AuthenticationScreen(
        onForgotPasswordClick = onForgotPasswordClick,
        state = state,
        onEvent = { viewModel.onEvent(it) },
        snackbarHostState = snackbarHostState
    )
}

@Composable
private fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    onForgotPasswordClick: () -> Unit,
    headBackGround: ImageVO = ImageVO.Resource(R.drawable.head_background_small),
    inTouchLogo: ImageVO = ImageVO.Resource(R.drawable.icon_intouch_logo),
    state: AuthScreenState,
    onEvent: (AuthenticationDataEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = headBackGround.painter(), contentDescription = "",
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Image(
                painter = inTouchLogo.painter(), contentDescription = "",
                modifier = Modifier.padding(top = 36.dp)
            )
        }
        Spacer(modifier = Modifier.height(72.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.welcome_to_intouch).value(),
                style = InTouchTheme.typography.titleMedium,
                color = InTouchTheme.colors.textGreen,
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.sign_in_to_continue).value(),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                value = state.login,
                onValueChange =
                {
                    onEvent(AuthenticationDataEvent.OnLoginTextChanged(it))
                },
                isPasswordVisible = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                error = state.isErrorLogin,
                caption = state.loginCaption,
                isPasswordVisibleIconVisible = false,
                onPasswordVisibleIconClick = {
                },
                hint = StringVO.Resource(care.intouch.app.R.string.e_mail),
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                value = state.password,
                onValueChange =
                {
                    onEvent(AuthenticationDataEvent.OnPasswordTextChanged(it))
                },
                isPasswordVisible = state.isPasswordVisible,
                isPasswordVisibleIconVisible = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                error = state.isErrorPassword,
                caption = state.passwordCaption,
                onPasswordVisibleIconClick = {
                    onEvent(AuthenticationDataEvent.OnPasswordIconClick)
                },
                hint = StringVO.Resource(care.intouch.app.R.string.password),
            )
            Spacer(modifier = Modifier.height(74.dp))
            PrimaryButtonGreen(
                onClick = {
                    onEvent(AuthenticationDataEvent.OnLoginButtonClicked)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                isEnabled =
                state.login.isNotEmpty() and state.password.isNotEmpty() and !state.isErrorLogin and !state.isErrorPassword,
                text = StringVO.Resource(care.intouch.app.R.string.login),
            )
            SecondaryButtonDark(
                onClick = { onForgotPasswordClick.invoke() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                textStyle = InTouchTheme.typography.bodyRegular,
                isEnabled = true,
                text = StringVO.Resource(care.intouch.app.R.string.forgot_password),
                enableTextColor = InTouchTheme.colors.textGreen
            )
            SnackbarHost(
                modifier = Modifier.navigationBarsPadding(),
                hostState = snackbarHostState
            ){
                IntouchSnackbar(data = it)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AuthenticationScreenPreview() {
    val state = AuthScreenState(
        password = "",
        login = "",
        loginCaption = StringVO.Plain(""),
        passwordCaption = StringVO.Plain(""),
        isPasswordVisible = false,
        isIconVisible = true,
        isErrorLogin = false,
        isErrorPassword = false,
    )
    InTouchTheme {
        AuthenticationScreen(
            onForgotPasswordClick = {},
            onEvent = {},
            state = state,
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}

