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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    var saveChangesButtonVisibility by rememberSaveable { mutableStateOf(false) }

    var nameTextFieldEnabled by rememberSaveable { mutableStateOf(false) }
    var lastNameTextFieldEnabled by rememberSaveable { mutableStateOf(false) }
    var emailTextFieldEnabled by rememberSaveable { mutableStateOf(false) }

    var nameButtonEnabled by rememberSaveable { mutableStateOf(true) }
    var lastNameButtonEnabled by rememberSaveable { mutableStateOf(true) }
    var emailButtonEnabled by rememberSaveable { mutableStateOf(true) }

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
                value = state.name,
                textFieldEnabled = nameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    if (it.length <= 20) {
                        viewModel.updateState(it, state.lastName, state.email)
                        //allDataIsValid = state.dataIsValid
                    }
                },
                onIconClick = {
                    nameTextFieldEnabled = true
                    saveChangesButtonVisibility = true
                    nameButtonEnabled = false
                    nameFocusRequester.requestFocus()
                },
                focusRequester = nameFocusRequester,
                buttonEnabled = nameButtonEnabled,
                icon = if (nameButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Plain("Last name").value(),
                value = state.lastName,
                textFieldEnabled = lastNameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    if (it.length <= 20) {
                        viewModel.updateState(state.name, it, state.email)
                        //allDataIsValid = state.dataIsValid

                    }
                },
                onIconClick = {
                    lastNameTextFieldEnabled = true
                    saveChangesButtonVisibility = true
                    lastNameButtonEnabled = false
                    lastNameFocusRequester.requestFocus()
                },
                focusRequester = lastNameFocusRequester,
                buttonEnabled = lastNameButtonEnabled,
                icon = if (lastNameButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Plain("E-mail").value(),
                value = state.email,
                textFieldEnabled = emailTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    if (it.length <= 20) {
                        viewModel.updateState(state.name, state.lastName, it)
                        //allDataIsValid = state.dataIsValid
                    }
                },
                onIconClick = {
                    emailTextFieldEnabled = true
                    saveChangesButtonVisibility = true
                    emailButtonEnabled = false
                    emailFocusRequester.requestFocus()
                },
                focusRequester = emailFocusRequester,
                buttonEnabled = emailButtonEnabled,
                icon = if (emailButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            if(state.dataIsValid){
                Spacer(modifier = Modifier.height(22.dp))
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                RowWithMessage(
                    successOrError = state.dataIsValid, //allDataIsValid,
                    messageText = StringVO.Plain(state.errorMessage),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (saveChangesButtonVisibility) {
                IntouchButton(
                    text = StringVO.Plain("Save changes").value(),
                    onClick = {
                        viewModel.sendDataInDomain()
                        saveChangesButtonVisibility = false
                        nameTextFieldEnabled = false
                        lastNameTextFieldEnabled = false
                        emailTextFieldEnabled = false
                        nameButtonEnabled = true
                        lastNameButtonEnabled = true
                        emailButtonEnabled = true
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