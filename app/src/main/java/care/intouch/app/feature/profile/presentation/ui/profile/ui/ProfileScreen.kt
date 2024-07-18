package care.intouch.app.feature.profile.presentation.ui.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataEvent
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileState
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.profile.PersonalData
import care.intouch.uikit.ui.profile.ProfileButton
import care.intouch.uikit.ui.profile.RowWithMessage
import care.intouch.uikit.ui.profile.TopPanelWithoutArrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    onSecurityClick: () -> Unit,
    onChangePinCode: () -> Unit,
    onSingOut: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    ProfileScreen(
        onSecurityClick = onSecurityClick,
        onChangePinCode = onChangePinCode,
        onSingOut = onSingOut,
        onEvent = { viewModel.onEvent(it) },
        state = state
    )
}

@Composable
private fun ProfileScreen(
    onSecurityClick: () -> Unit,
    onChangePinCode: () -> Unit,
    onSingOut: () -> Unit,
    onEvent: (ProfileDataEvent) -> Unit,
    state: ProfileState
) {
    val scope = rememberCoroutineScope()

    val nameFocusRequester = remember { FocusRequester() }
    val lastNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier.background(InTouchTheme.colors.input85)
    ) {
        Image(
            painter = painterResource(R.drawable.head_background_small_h165),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(66.dp))
            TopPanelWithoutArrow(text = StringVO.Resource(resId = care.intouch.app.R.string.profile_title))
            Spacer(modifier = Modifier.height(104.dp))
            PersonalData(
                naming = StringVO.Resource(resId = care.intouch.app.R.string.name_info_profile),
                value = state.name.value(),
                textFieldEnabled = state.nameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    onEvent(ProfileDataEvent.OnName(name = it))
                },
                onIconClick = {
                    if (state.dataIsValid) {
                        onEvent(ProfileDataEvent.OnEditNameButtonClick())
                        scope.launch {
                            delay(200)  // the delay of 0,2 seconds
                            nameFocusRequester.requestFocus()
                        }
                    }
                },
                focusRequester = nameFocusRequester,
                buttonEnabled = state.nameButtonEnabled,
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Resource(resId = care.intouch.app.R.string.last_name_info_profile),
                value = state.lastName.value(),
                textFieldEnabled = state.lastNameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    onEvent(ProfileDataEvent.OnLastName(lastName = it))
                },
                onIconClick = {
                    if (state.dataIsValid) {
                        onEvent(ProfileDataEvent.OnEditLastNameButtonClick())
                        scope.launch {
                            delay(200)  // the delay of 0,2 seconds
                            lastNameFocusRequester.requestFocus()
                        }
                    }
                },
                focusRequester = lastNameFocusRequester,
                buttonEnabled = state.lastNameButtonEnabled,
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Resource(resId = care.intouch.app.R.string.email_info_profile),
                value = state.email.value(),
                textFieldEnabled = state.emailTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    onEvent(ProfileDataEvent.OnEmail(email = it))
                },
                onIconClick = {
                    if (state.dataIsValid) {
                        onEvent(ProfileDataEvent.OnEditEmailButtonClick())
                        scope.launch {
                            delay(200)  // the delay of 0,2 seconds
                            emailFocusRequester.requestFocus()
                        }
                    }
                },
                focusRequester = emailFocusRequester,
                buttonEnabled = state.emailButtonEnabled,
            )
            if (state.dataIsValid) {  // Show or not a message about data incorrectness
                Spacer(modifier = Modifier.height(22.dp))
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                RowWithMessage(
                    textIsGreenOrRed = false,
                    messageText = state.dataIsNotValidMessage,
                )
            }

            if (state.nameResponseHasBeenReceived) {  // Show message about name request result
                RowWithMessage(
                    textIsGreenOrRed = state.nameResponseIsSuccess,
                    messageText = state.resultMessageOfChangeNameRequest,
                )
            }
            if (state.emailResponseIsSuccess) {  // Show message about email request result
                if (state.nameResponseHasBeenReceived){
                    Spacer(modifier = Modifier.height(8.dp))
                }
                RowWithMessage(
                    textIsGreenOrRed = state.emailColorMessageIsGreenOrRed,
                    messageText = state.resultMessageOfChangeEmailRequest,
                )
            }
            if (state.nameResponseHasBeenReceived || state.emailResponseIsSuccess || !state.dataIsValid) {
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (state.saveChangesButtonVisibility) {
                IntouchButton(
                    text = StringVO.Resource(resId = care.intouch.app.R.string.save_changes_button),
                    onClick = {
                        onEvent(ProfileDataEvent.OnSaveChangesButtonClick())
                    },
                    isEnabled = state.dataIsValid,
                    contentPadding = PaddingValues(horizontal = 72.dp, vertical = 16.dp),
                    modifier = Modifier.align(CenterHorizontally),
                )
            } else {
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(18.dp))

            ProfileButton(
                onClick = { onSecurityClick.invoke() },
                text = StringVO.Resource(resId = care.intouch.app.R.string.security_profile),
                enableBackgroundColor = InTouchTheme.colors.input,
                disableBackgroundColor = InTouchTheme.colors.input,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            HorizontalDivider(
                color = InTouchTheme.colors.accentGreen30,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp)
            )
            ProfileButton(
                onClick = { onChangePinCode.invoke() },
                text = StringVO.Resource(resId = care.intouch.app.R.string.create_pin_profile),
                enableBackgroundColor = InTouchTheme.colors.input,
                disableBackgroundColor = InTouchTheme.colors.input,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            HorizontalDivider(
                color = InTouchTheme.colors.accentGreen30,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp)
            )
        }
        PrimaryButtonWhite(
            text = StringVO.Resource(resId = care.intouch.app.R.string.sign_out_button),
            onClick = {
                onEvent(ProfileDataEvent.OnSingOutButtonClick())
                onSingOut.invoke()
            },
            modifier = Modifier
                .align(BottomCenter)
                .padding(bottom = 100.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    val profileData = ProfileState(
        dataIsValid = true,
        name = StringVO.Plain("MyName"),
        nameIsValid = true,
        lastName = StringVO.Plain("MyLastName"),
        lastNameIsValid = true,
        email = StringVO.Plain("gogo@gmail.com"),
        emailIsValid = true,
        resultMessageOfChangeEmailRequest = StringVO.Resource(care.intouch.app.R.string.info_about_change_profile_data),
        emailColorMessageIsGreenOrRed = false,
        emailResponseIsSuccess = true,
        saveChangesButtonVisibility = true
    )
    val state = profileData

    InTouchTheme {
        ProfileScreen(
            onSecurityClick = {},
            onChangePinCode = {},
            onSingOut = {},
            onEvent = {},
            state = state
        )
    }
}