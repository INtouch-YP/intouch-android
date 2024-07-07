package care.intouch.app.feature.authorization.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.State
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import care.intouch.app.feature.authorization.presentation.ui.AuthorizationSideEffect
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun AuthorizationScreenInit(
    userId: String? = null,
    token: String? = null,
    onGoPinCodeInstallationScreen: () -> Unit,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect {
            when(it) {
                AuthorizationSideEffect.NavigateToCreatePinCode -> {
                    onGoPinCodeInstallationScreen()
                }
            }
        }
    }

    AuthorizationScreen(
        userId = userId,
        token = token,
        onEvent = { viewModel.onEvent(it) },
        state = state
    )
}

@Composable
private fun AuthorizationScreen(
    userId: String? = null,
    token: String? = null,
    onEvent: (AuthorizationEvent) -> Unit,
    state: State<AuthorizationState>
) {
    onEvent(AuthorizationEvent.OnGetUserInfo(userId, token))

    Scaffold { paddingValues ->
        when (state.value.uiState) {
            AuthorizationUiState.SetPassword -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InTouchTheme.colors.white)
                        .padding(paddingValues),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(172.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = R.drawable.head_background_small),
                            contentDescription = null
                        )
                        Image(
                            painter = painterResource(id = R.drawable.head_logo),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    SetPasswordScreen(
                        userName = state.value.userName,
                        isPasswordValid = state.value.passwordValidType,
                        isConfirmPasswordValid = state.value.confirmPasswordValidType,
                        isEnable = state.value.isEnable,
                        isEnableAgreementToTerm = state.value.isEnableAgreementToTerm,
                        onEvent = onEvent
                    )
                }
            }

            AuthorizationUiState.Authorized -> {
                onEvent(AuthorizationEvent.OnCreatePinCodeButtonClick)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AuthorizationScreenPreview() {
    InTouchTheme {
        AuthorizationScreenInit(
            userId = "12321",
            token = "sdfsdfglkrl;er;lge;l",
            onGoPinCodeInstallationScreen = {},
        )
    }
}