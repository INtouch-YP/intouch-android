package care.intouch.app.feature.profile.presentation.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.profile.PersonalData
import care.intouch.uikit.ui.profile.ProfileButton
import care.intouch.uikit.ui.profile.RowWithMessage
import care.intouch.uikit.ui.profile.TopPanel

@Composable
fun ProfileScreen(
    onSecurityClick: () -> Unit,
    onChangePinCode: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val viewsState by viewModel.viewsState.collectAsState()

    val nameFocusRequester = remember { FocusRequester() }
    val lastNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier.background(InTouchTheme.colors.input)
    ) {
        Image(
            painter = painterResource(R.drawable.head_background_small_h165),
            contentDescription = null,
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(66.dp))
            TopPanel(text = StringVO.Plain("Profile"))
            Spacer(modifier = Modifier.height(104.dp))
            PersonalData(
                naming = StringVO.Plain("Name").value(),
                value = state.name.data,
                textFieldEnabled = viewsState.nameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    viewModel.updateName(it)
                },
                onIconClick = {
                    if (state.dataIsValid) {
                        viewModel.changeTextFieldsAndButtonsEnabled(
                            name = true,
                            lastName = false,
                            email = false,
                            saveChangesButton = true,
                            infIsUpdate = false
                        )
                        nameFocusRequester.requestFocus()
                    }
                },
                focusRequester = nameFocusRequester,
                buttonEnabled = viewsState.nameButtonEnabled,
                icon = if (viewsState.nameButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Plain("Last name").value(),
                value = state.lastName.data,
                textFieldEnabled = viewsState.lastNameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    viewModel.updateLastName(it)
                },
                onIconClick = {
                    if (state.dataIsValid) {
                        viewModel.changeTextFieldsAndButtonsEnabled(
                            name = false,
                            lastName = true,
                            email = false,
                            saveChangesButton = true,
                            infIsUpdate = false
                        )
                        lastNameFocusRequester.requestFocus()
                    }
                },
                focusRequester = lastNameFocusRequester,
                buttonEnabled = viewsState.lastNameButtonEnabled,
                icon = if (viewsState.lastNameButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Plain("E-mail").value(),
                value = state.email.data,
                textFieldEnabled = viewsState.emailTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                        viewModel.updateEmail(it)
                },
                onIconClick = {
                    if (state.dataIsValid) {
                        viewModel.changeTextFieldsAndButtonsEnabled(
                            name = false,
                            lastName = false,
                            email = true,
                            saveChangesButton = true,
                            infIsUpdate = false
                        )
                        emailFocusRequester.requestFocus()
                    }
                },
                focusRequester = emailFocusRequester,
                buttonEnabled = viewsState.emailButtonEnabled,
                icon = if (viewsState.emailButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            if (state.dataIsValid) {  // Show or not a message about data incorrectness
                Spacer(modifier = Modifier.height(22.dp))
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                RowWithMessage(
                    successOrError = state.dataIsValid,
                    messageText = StringVO.Plain(state.errorMessage),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (viewsState.informationIsUpdate) {    // Show message "Information successfully updated"
                RowWithMessage(
                    successOrError = state.dataIsValid,
                    messageText = StringVO.Plain(state.successMessage),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (viewsState.saveChangesButtonVisibility) {
                IntouchButton(
                    text = StringVO.Plain("Save changes").value(),
                    onClick = {
                        viewModel.sendDataInDomain()
                        viewModel.changeTextFieldsAndButtonsEnabled(
                            name = false,
                            lastName = false,
                            email = false,
                            saveChangesButton = false,
                            infIsUpdate = true
                        )
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
                text = StringVO.Plain("Security"),
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
                text = StringVO.Plain("Create PIN code"),
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
            text = "Sing out",
            onClick = {},
            modifier = Modifier
                .align(BottomCenter)
                .padding(bottom = 96.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    InTouchTheme {
        ProfileScreen(
            onSecurityClick = {},
            onChangePinCode = {}
        )
    }
}