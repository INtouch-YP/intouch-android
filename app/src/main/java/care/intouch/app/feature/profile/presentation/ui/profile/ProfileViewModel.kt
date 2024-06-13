package care.intouch.app.feature.profile.presentation.ui.profile

import androidx.lifecycle.ViewModel
import care.intouch.app.R
import care.intouch.app.feature.profile.presentation.ui.profile.models.ChangeProfileDataEvent
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileInformationData
import care.intouch.app.feature.profile.presentation.ui.profile.models.ResultOfCheckProfileData
import care.intouch.app.feature.profile.presentation.ui.profile.models.ViewsComponentsState
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
    private var _viewsState = MutableStateFlow(ViewsComponentsState())
    val viewsState = _viewsState.asStateFlow()



    fun updateState(event: ChangeProfileDataEvent){
        when (event) {
            is  ChangeProfileDataEvent.OnChangeName -> {
                updateName(event)
            }
            is ChangeProfileDataEvent.OnChangeLastName -> {
                updateLastName(event)
            }
            is ChangeProfileDataEvent.OnChangeEmail -> {
                updateEmail(event)
            }
        }
    }

    private fun updateName(event: ChangeProfileDataEvent.OnChangeName) {
        if (event.name.length <= MAX_NAME_LENGTH) {
            val isTextValid = isTextValid(event.name)
            val isNameValid = isTextValid && (event.name.length > 2)
            var errorMessage: StringVO = StringVO.Plain("")
            if (!isTextValid) {
                errorMessage = event.errorInvalidChar
            }
            if (event.name.length <= 2) {
                errorMessage = event.errorLength
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isNameValid,
                    name = ProfileInformationData(StringVO.Plain(event.name), isNameValid),
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    errorMessage = errorMessage,
                    successMessage = _state.value.successMessage
                )
            }
        }
    }

    private fun updateLastName(event: ChangeProfileDataEvent.OnChangeLastName) {
        if (event.lastName.length <= MAX_NAME_LENGTH) {
            val isTextValid = isTextValid(event.lastName)
            val isLastNameValid = isTextValid && (event.lastName.length > 2)
            var errorMessage: StringVO = StringVO.Plain("")
            if (!isTextValid) {
                errorMessage = event.errorInvalidChar
            }
            if (event.lastName.length <= 2) {
                errorMessage = event.errorLength
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isLastNameValid,
                    name = _state.value.name,
                    lastName = ProfileInformationData(StringVO.Plain(event.lastName), isLastNameValid),
                    email = _state.value.email,
                    errorMessage = errorMessage,
                    successMessage = _state.value.successMessage
                )
            }
        }
    }


    private fun updateEmail(event: ChangeProfileDataEvent.OnChangeEmail) {
        if (event.email.length <= MAX_EMAIL_LENGTH) {
            val isEmailValid = isEmailValid(event.email)
            var errorMessage: StringVO = StringVO.Plain("")
            if (!isEmailValid) {
                errorMessage = event.errorEmailNotValid
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isEmailValid,
                    name = _state.value.name,
                    lastName = _state.value.lastName,
                    email = ProfileInformationData(StringVO.Plain(event.email), isEmailValid),
                    errorMessage = errorMessage,
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
            successMessage = StringVO.Resource(R.string.info_about_change_profile_data)
        )
    }

    private fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex("[a-zA-Z- \\.]*")
        return regex.matches(text)
    }

    private companion object {
        private const val MAX_NAME_LENGTH = 20
        private const val MAX_EMAIL_LENGTH = 30
    }
}