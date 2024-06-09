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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun AuthorizationScreen(
    navController: NavController,
    userId: String? = null,
    token: String? = null,
    onGoPinCodeInstallationScreen: () -> Unit,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    viewModel.onEvent(AuthorizationEvent.OnGetUserInfo(userId, token))

    Scaffold { paddingValues ->
        when (state.value.uiState) {
            AuthorizationUiState.Loading -> {

            }

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
                        errorPassword = state.value.error != null,
                        errorPasswordText = state.value.error?.let { getTextError(it) } ?: "",
                        onEvent = viewModel::onEvent
                    )
                }
            }

            AuthorizationUiState.Authorized -> {
                onGoPinCodeInstallationScreen.invoke()
            }
        }
    }
}

@Composable
fun getTextError(errorType: InputPasswordError) = when (errorType) {
    InputPasswordError.NotMatch -> stringResource(id = care.intouch.app.R.string.password_not_match_error)
}