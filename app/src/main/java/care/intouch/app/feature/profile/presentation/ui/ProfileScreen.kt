package care.intouch.app.feature.profile.presentation.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.profile.PersonalData
import care.intouch.uikit.ui.profile.ProfileButton
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
    //profileViewModel: ProfileViewModel = ProfileViewModel()
) {
    var textName by rememberSaveable { mutableStateOf("MyName") }
    var textLastName by rememberSaveable { mutableStateOf("MyLastName") }
    var textEmail by rememberSaveable { mutableStateOf("gogo@gmail.com") }
    var saveChangesButtonState by rememberSaveable { mutableStateOf(false) }
    var nameTextFieldEnabled by rememberSaveable { mutableStateOf(false) }
    var lastNameTextFieldEnabled by rememberSaveable { mutableStateOf(false) }
    var emailTextFieldEnabled by rememberSaveable { mutableStateOf(false) }

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
                value = textName,
                textFieldEnabled = nameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = {
                    textName = it
                },
                onIconClick = {
                    nameTextFieldEnabled = true
                    saveChangesButtonState = true
                    nameFocusRequester.requestFocus()
                },
                focusRequester = nameFocusRequester
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Plain("Last name").value(),
                value = textLastName,
                textFieldEnabled = lastNameTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = { textLastName = it },
                onIconClick = {
                    lastNameTextFieldEnabled = true
                    saveChangesButtonState = true
                    lastNameFocusRequester.requestFocus()
                },
                focusRequester = lastNameFocusRequester
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonalData(
                naming = StringVO.Plain("E-mail").value(),
                value = textEmail,
                textFieldEnabled = emailTextFieldEnabled,
                modifier = Modifier.padding(horizontal = 32.dp),
                onValueChange = { textEmail = it },
                onIconClick = {
                    emailTextFieldEnabled = true
                    saveChangesButtonState = true
                    emailFocusRequester.requestFocus()
                },
                focusRequester = emailFocusRequester
            )
            Spacer(modifier = Modifier.height(22.dp))
            if (saveChangesButtonState) {
                IntouchButton(
                    text = StringVO.Plain("Save changes").value(),
                    onClick = {
                        saveChangesButtonState = false
                        nameTextFieldEnabled = false
                        lastNameTextFieldEnabled = false
                        emailTextFieldEnabled = false
                    },
                    isEnabled = checkBasicTextFields(textName, textLastName, textEmail),
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
                disableBackgroundColor = InTouchTheme.colors.input
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
                disableBackgroundColor = InTouchTheme.colors.input
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