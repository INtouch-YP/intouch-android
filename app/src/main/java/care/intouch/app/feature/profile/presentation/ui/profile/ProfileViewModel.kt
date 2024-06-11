package care.intouch.app.feature.profile.presentation.ui.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {

    private var _state = MutableStateFlow(loadProfileData())
    val state = _state.asStateFlow()
    private var _viewsState = MutableStateFlow(getDefaultViewState())
    val viewsState = _viewsState.asStateFlow()


    @Composable
    fun UpdateName(name: StringVO) {
        if (name.value().length <= MAX_NAME_LENGTH) {
            val isTextValid = isTextValid(name.value())
            val isNameValid = isTextValid && (name.value().length > 2)
            var errorMessage = ""
            if (!isTextValid) {
                errorMessage =
                    StringVO.Resource(resId = R.string.profile_invalid_char_error).value()
            }
            if (name.value().length <= 2) {
                errorMessage = StringVO.Resource(resId = R.string.profile_small_name_error).value()
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isNameValid,
                    name = ProfileInformationData(name, isNameValid),
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    errorMessage = StringVO.Plain(errorMessage),
                    successMessage = _state.value.successMessage
                )
            }
        }
    }

    @Composable
    fun UpdateLastName(lastName: StringVO) {
        if (lastName.value().length <= MAX_NAME_LENGTH) {
            val isTextValid = isTextValid(lastName.value())
            val isLastNameValid = isTextValid && (lastName.value().length > 2)
            var errorMessage = ""
            if (!isTextValid) {
                errorMessage =
                    StringVO.Resource(resId = R.string.profile_invalid_char_error).value()
            }
            if (lastName.value().length <= 2) {
                errorMessage =
                    StringVO.Resource(resId = R.string.profile_small_last_name_error).value()
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isLastNameValid,
                    name = _state.value.name,
                    lastName = ProfileInformationData(lastName, isLastNameValid),
                    email = _state.value.email,
                    errorMessage = StringVO.Plain(errorMessage),
                    successMessage = _state.value.successMessage
                )
            }
        }
    }

    @Composable
    fun UpdateEmail(email: StringVO) {
        if (email.value().length <= MAX_EMAIL_LENGTH) {
            val isEmailValid = isEmailValid(email.value())
            var errorMessage = ""
            if (!isEmailValid) {
                errorMessage = StringVO.Resource(resId = R.string.email_not_valid_error).value()
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isEmailValid,
                    name = _state.value.name,
                    lastName = _state.value.lastName,
                    email = ProfileInformationData(email, isEmailValid),
                    errorMessage = StringVO.Plain(errorMessage),
                    successMessage = _state.value.successMessage
                )
            }
        }
    }

    fun sendDataInDomain() {

    }

    fun changeTextFieldsAndButtonsEnabled(
        name: Boolean,
        lastName: Boolean,
        email: Boolean,
        saveChangesButton: Boolean,
        infIsUpdate: Boolean
    ) {
        _viewsState.update {
            ViewsComponentsState(
                saveChangesButtonVisibility = saveChangesButton,
                informationIsUpdate = infIsUpdate,
                nameTextFieldEnabled = name,
                lastNameTextFieldEnabled = lastName,
                emailTextFieldEnabled = email,
                nameButtonEnabled = !name,
                lastNameButtonEnabled = !lastName,
                emailButtonEnabled = !email,
            )
        }
    }

    private fun loadProfileData(): ResultOfCheckProfileData {
        return ResultOfCheckProfileData(
            dataIsValid = true,
            name = ProfileInformationData(StringVO.Plain("MyName"), true),
            lastName = ProfileInformationData(StringVO.Plain("MyLastName"), true),
            email = ProfileInformationData(StringVO.Plain("gogo@gmail.com"), true),
            errorMessage = StringVO.Plain(""),
            successMessage = StringVO.Resource(resId = R.string.info_about_change_profile_data)
        )
    }

    private fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex("[a-zA-Z- \\.]*")
        return regex.matches(text)
    }

    private fun getDefaultViewState(): ViewsComponentsState {
        return ViewsComponentsState(
            saveChangesButtonVisibility = false,
            informationIsUpdate = false,
            nameTextFieldEnabled = false,
            lastNameTextFieldEnabled = false,
            emailTextFieldEnabled = false,
            nameButtonEnabled = true,
            lastNameButtonEnabled = true,
            emailButtonEnabled = true,
        )
    }

    private companion object {
        private const val MAX_NAME_LENGTH = 20
        private const val MAX_EMAIL_LENGTH = 30
    }
}