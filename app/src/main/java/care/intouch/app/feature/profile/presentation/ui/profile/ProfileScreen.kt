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


private fun checkBasicTextFields(
    textName: String,
    textLastName: String,
    textEmail: String
): Boolean {
    return !(textName.isEmpty() || textLastName.isEmpty() || textEmail.isEmpty())
}

@Composable
fun ProfileScreen(
    onSecurityClick: () -> Unit,
    onChangePinCode: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

//    var textName by rememberSaveable { mutableStateOf("MyName") }
//    var textLastName by rememberSaveable { mutableStateOf("MyLastName") }
//    var textEmail by rememberSaveable { mutableStateOf("gogo@gmail.com") }

    var saveChangesButtonState by rememberSaveable { mutableStateOf(false) }

    var nameTextFieldEnabled by rememberSaveable { mutableStateOf(false) }
    var lastNameTextFieldEnabled by rememberSaveable { mutableStateOf(false) }
    var emailTextFieldEnabled by rememberSaveable { mutableStateOf(false) }

    var nameButtonEnabled by rememberSaveable { mutableStateOf(true) }
    var lastNameButtonEnabled by rememberSaveable { mutableStateOf(true) }
    var emailButtonEnabled by rememberSaveable { mutableStateOf(true) }

    val nameFocusRequester = remember { FocusRequester() }
    val lastNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }


    var allDataIsValid by rememberSaveable { mutableStateOf(true) }

//    var resultOfCheckData = viewModel.checkProfileData(
//        textName,
//        textLastName,
//        textEmail
//    )

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
                value = state.value.name,
                textFieldEnabled = nameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    if (it.length <= 20) {
                        viewModel.updateState(it, state.value.lastName, state.value.email)
                        allDataIsValid = state.value.dataIsValid
                    }
                },
                onIconClick = {
                    nameTextFieldEnabled = true
                    saveChangesButtonState = true
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
                value = state.value.lastName,
                textFieldEnabled = lastNameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    if (it.length <= 20) {
                        viewModel.updateState(state.value.name, it, state.value.email)
                        allDataIsValid = state.value.dataIsValid
                    }
                },
                onIconClick = {
                    lastNameTextFieldEnabled = true
                    saveChangesButtonState = true
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
                value = state.value.email,
                textFieldEnabled = emailTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    if (it.length <= 20) {
                        viewModel.updateState(state.value.name, state.value.lastName, it)
                        allDataIsValid = state.value.dataIsValid
                    }
                },
                onIconClick = {
                    emailTextFieldEnabled = true
                    saveChangesButtonState = true
                    emailButtonEnabled = false
                    emailFocusRequester.requestFocus()
                },
                focusRequester = emailFocusRequester,
                buttonEnabled = emailButtonEnabled,
                icon = if (emailButtonEnabled) ImageVO.Resource(R.drawable.icon_edit) else ImageVO.Resource(
                    R.drawable.icon_edit_light
                )
            )
            if(allDataIsValid){
                Spacer(modifier = Modifier.height(22.dp))
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                RowWithMessage(
                    successOrError = allDataIsValid,
                    messageText = StringVO.Plain(state.value.message),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (saveChangesButtonState) {
                IntouchButton(
                    text = StringVO.Plain("Save changes").value(),
                    onClick = {
                        viewModel.sendDataInDomain()
                        saveChangesButtonState = false
                        nameTextFieldEnabled = false
                        lastNameTextFieldEnabled = false
                        emailTextFieldEnabled = false
                        nameButtonEnabled = true
                        lastNameButtonEnabled = true
                        emailButtonEnabled = true

                    },
                    //isEnabled = checkBasicTextFields(textName, textLastName, textEmail),
                    isEnabled = allDataIsValid,
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