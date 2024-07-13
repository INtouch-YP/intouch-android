package care.intouch.app.feature.profile.presentation.ui.security

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import care.intouch.app.R
import care.intouch.app.feature.profile.presentation.ui.security.models.SecuritySideEffect
import care.intouch.app.feature.profile.presentation.ui.security.models.SecurityState
import care.intouch.app.feature.profile.presentation.ui.security.models.SecurityUiState
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun SecurityScreenInit(
    onPopBackStack: () -> Unit,
    onDeleteProfileForeverClick: () -> Unit,
    viewModel: SecurityViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                SecuritySideEffect.NavigateBack -> {
                    onPopBackStack.invoke()
                }

                SecuritySideEffect.NavigateToDeleteProfile -> {
                    onDeleteProfileForeverClick.invoke()
                }
            }
        }
    }

    SecurityScreen(
        onEvent = { viewModel.onEvent(it) },
        state = state
    )
}

@Composable
private fun SecurityScreen(
    onEvent: (SecurityEvent) -> Unit,
    state: State<SecurityState>
) {
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        when (state.value.uiState) {
            SecurityUiState.SetPassword -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InTouchTheme.colors.white)
                        .padding(bottom = paddingValues.calculateBottomPadding())
                        .verticalScroll(scrollState),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = care.intouch.uikit.R.drawable.head_background_small),
                            contentDescription = null
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.clickable {
                                    onEvent(SecurityEvent.OnBackButtonClick)
                                },
                                painter = painterResource(id = care.intouch.uikit.R.drawable.icon_arrow_left),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 24.dp),
                                text = stringResource(id = R.string.security_title),
                                textAlign = TextAlign.Center,
                                color = InTouchTheme.colors.textBlue,
                                style = InTouchTheme.typography.titleLarge,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    SecuritySetPasswordScreen(
                        currentPasswordValid = state.value.currentPasswordValidType,
                        isSuccessUpdate = state.value.isSuccessUpdate,
                        isPasswordValid = state.value.passwordValidType,
                        isConfirmPasswordValid = state.value.confirmPasswordValidType,
                        isEnable = state.value.isEnable,
                        onEvent = onEvent
                    )
                }
            }

            SecurityUiState.DeleteProfile -> {

                Popup(
                    alignment = Alignment.Center,
                    onDismissRequest = {
                        onEvent(SecurityEvent.OnCancelDeleteProfile)
                    }
                ) {
                    DeleteProfilePopUp(
                        modifier = Modifier
                            .width(334.dp)
                            .height(501.dp),
                        onEvent =  onEvent
                    )
                }
            }

            SecurityUiState.ProfileDeleted -> {
                onEvent(SecurityEvent.OnDeleteProfileButtonClick)
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun SecurityScreenPreview() {
    InTouchTheme {
        SecurityScreenInit(
            onPopBackStack = {},
            onDeleteProfileForeverClick = {},
        )
    }
}