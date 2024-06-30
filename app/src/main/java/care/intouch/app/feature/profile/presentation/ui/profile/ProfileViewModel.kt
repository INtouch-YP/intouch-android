package care.intouch.app.feature.profile.presentation.ui.profile

import androidx.lifecycle.ViewModel
import care.intouch.app.R
import care.intouch.app.feature.profile.presentation.ui.profile.models.ChangeProfileDataEvent
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileInformationData
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataState
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileState
import care.intouch.app.feature.profile.presentation.ui.profile.models.ViewsComponentsState
import care.intouch.app.feature.profile.presentation.ui.profile.string_extension.replaceChars
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileState(loadProfileData(), ViewsComponentsState()))
    val state = _state.asStateFlow()

    fun onEvent(event: ChangeProfileDataEvent) {
        when (event) {
            is ChangeProfileDataEvent.OnChangeName -> {
                updateName(event)
            }

            is ChangeProfileDataEvent.OnChangeLastName -> {
                updateLastName(event)
            }

            is ChangeProfileDataEvent.OnChangeEmail -> {
                updateEmail(event)
            }

            is ChangeProfileDataEvent.OnEditEmailButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ChangeProfileDataEvent.OnEditLastNameButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ChangeProfileDataEvent.OnEditNameButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ChangeProfileDataEvent.OnSaveChangesButtonClick -> {
                sendDataInDomain()
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }
        }
    }

    private fun updateName(event: ChangeProfileDataEvent.OnChangeName) {
        if (event.name.length <= MAX_NAME_LENGTH) {
            val name = event.name.replaceChars()
            val isTextValid = isTextValid(name)
            val isNameValid = isTextValid && (name.length > 2)
            var errorMessage: StringVO = StringVO.Plain("")
            if (!isTextValid) {
                errorMessage = event.errorInvalidChar
            }
            if (name.length <= 2) {
                errorMessage = event.errorLength
            }
            _state.update {
                ProfileState(
                    profileDataState = ProfileDataState(
                        dataIsValid = isNameValid,
                        name = ProfileInformationData(StringVO.Plain(name), isNameValid),
                        lastName = _state.value.profileDataState.lastName,
                        email = _state.value.profileDataState.email,
                        errorMessage = errorMessage,
                        successMessage = _state.value.profileDataState.successMessage
                    ),
                    viewsComponentsState = _state.value.viewsComponentsState
                )
            }
        }
    }

    private fun updateLastName(event: ChangeProfileDataEvent.OnChangeLastName) {
        if (event.lastName.length <= MAX_NAME_LENGTH) {
            val lastName = event.lastName.replaceChars()
            val isTextValid = isTextValid(lastName)
            val isLastNameValid = isTextValid && (lastName.length > 2)
            var errorMessage: StringVO = StringVO.Plain("")
            if (!isTextValid) {
                errorMessage = event.errorInvalidChar
            }
            if (lastName.length <= 2) {
                errorMessage = event.errorLength
            }
            _state.update {
                ProfileState(
                    profileDataState = ProfileDataState(
                        dataIsValid = isLastNameValid,
                        name = _state.value.profileDataState.name,
                        lastName = ProfileInformationData(
                            StringVO.Plain(lastName),
                            isLastNameValid
                        ),
                        email = _state.value.profileDataState.email,
                        errorMessage = errorMessage,
                        successMessage = _state.value.profileDataState.successMessage
                    ),
                    viewsComponentsState = _state.value.viewsComponentsState
                )
            }
        }
    }

    private fun updateEmail(event: ChangeProfileDataEvent.OnChangeEmail) {
        val email =  event.email
        val isEmailValid = isEmailValid(email)
        var errorMessage: StringVO = StringVO.Plain("")
        if (!isEmailValid) {
            errorMessage = event.errorEmailNotValid
        }
        _state.update {
            ProfileState(
                profileDataState = ProfileDataState(
                    dataIsValid = isEmailValid,
                    name = _state.value.profileDataState.name,
                    lastName = _state.value.profileDataState.lastName,
                    email = ProfileInformationData(StringVO.Plain(email), isEmailValid),
                    errorMessage = errorMessage,
                    successMessage = _state.value.profileDataState.successMessage
                ),
                viewsComponentsState = _state.value.viewsComponentsState
            )
        }
    }

    private fun sendDataInDomain() {

    }

    private fun changeTextFieldsAndButtonsEnabled(
        name: Boolean,
        lastName: Boolean,
        email: Boolean,
        saveChangesButton: Boolean,
        infIsUpdate: Boolean
    ) {
        _state.update {
            ProfileState(
                profileDataState = _state.value.profileDataState,
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
            )
        }
    }

    private fun loadProfileData(): ProfileDataState {
        return ProfileDataState(
            dataIsValid = true,
            name = ProfileInformationData(StringVO.Plain("MyName"), true),
            lastName = ProfileInformationData(StringVO.Plain("MyLastName"), true),
            email = ProfileInformationData(StringVO.Plain("gogo@gmail.com"), true),
            errorMessage = StringVO.Plain(""),
            successMessage = StringVO.Resource(R.string.info_about_change_profile_data)
        )
    }

    private fun isEmailValid(text: String): Boolean {
        val regex = Regex(REGEX_EMAIL_ADDRESS)
        return regex.matches(text)
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex(NAME_REGEX)
        return regex.matches(text)
    }

    private companion object {
        private const val MAX_NAME_LENGTH = 20
        private const val REGEX_EMAIL_ADDRESS = "[a-zA-Z0-9\\.\\_\\-]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                ")+"
        private const val NAME_REGEX = "[a-zA-Z- \\.]*"
    }
}