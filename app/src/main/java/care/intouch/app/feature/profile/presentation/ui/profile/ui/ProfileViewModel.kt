package care.intouch.app.feature.profile.presentation.ui.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataUseCase
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataEvent
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileInformationData
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataState
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileState
import care.intouch.app.feature.profile.presentation.ui.profile.models.ViewsComponentsState
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userStorage: UserStorage,
    private val redactUserDataUseCase: RedactUserDataUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileState(loadProfileData(), ViewsComponentsState()))
    val state = _state.asStateFlow()
    private var userData: User? = null
    private var profileData: ProfileData = ProfileData("", "", "")

    fun onEvent(event: ProfileDataEvent) {
        when (event) {
            is ProfileDataEvent.OnName -> {
                updateName(event)
            }

            is ProfileDataEvent.OnLastName -> {
                updateLastName(event)
            }

            is ProfileDataEvent.OnEmail -> {
                updateEmail(event)
            }

            is ProfileDataEvent.OnEditEmailButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ProfileDataEvent.OnEditLastNameButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ProfileDataEvent.OnEditNameButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ProfileDataEvent.OnSaveChangesButtonClick -> {
                doPatchUserData()
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton,
                    infIsUpdate = event.infIsUpdate,
                )
            }

            is ProfileDataEvent.OnSingOutButtonClick -> {
                signOut()
            }

            is ProfileDataEvent.OnCreate -> {
                onCreate()
            }
        }
    }

    private fun onCreate(){
        readUserDataFromSharedPreferences()
    }

    private fun updateName(event: ProfileDataEvent.OnName) {
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
            profileData = profileData.copy(
                name = event.name
            )
            _state.update {
                ProfileState(
                    profileDataState = ProfileDataState(
                        dataIsValid = isNameValid,
                        name = ProfileInformationData(StringVO.Plain(event.name), isNameValid),
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

    private fun updateLastName(event: ProfileDataEvent.OnLastName) {
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
            profileData = profileData.copy(
                lastName = event.lastName
            )
            _state.update {
                ProfileState(
                    profileDataState = ProfileDataState(
                        dataIsValid = isLastNameValid,
                        name = _state.value.profileDataState.name,
                        lastName = ProfileInformationData(
                            StringVO.Plain(event.lastName),
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

    private fun updateEmail(event: ProfileDataEvent.OnEmail) {
        if (event.email.length <= MAX_EMAIL_LENGTH) {
            val isEmailValid = isEmailValid(event.email)
            var errorMessage: StringVO = StringVO.Plain("")
            if (!isEmailValid) {
                errorMessage = event.errorEmailNotValid
            }
            profileData = profileData.copy(
                email = event.email
            )
            _state.update {
                ProfileState(
                    profileDataState = ProfileDataState(
                        dataIsValid = isEmailValid,
                        name = _state.value.profileDataState.name,
                        lastName = _state.value.profileDataState.lastName,
                        email = ProfileInformationData(StringVO.Plain(event.email), isEmailValid),
                        errorMessage = errorMessage,
                        successMessage = _state.value.profileDataState.successMessage
                    ),
                    viewsComponentsState = _state.value.viewsComponentsState
                )
            }
        }
    }

    private fun readUserDataFromSharedPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataFromSharedPreferences: User = userStorage.read()
            profileData = ProfileData(
                name = dataFromSharedPreferences.firstName,
                lastName = dataFromSharedPreferences.lastName,
                email = dataFromSharedPreferences.email
            )
            userData = dataFromSharedPreferences
        }
    }

    private fun doPatchUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            redactUserDataUseCase.invoke(profileData, userData!!.id)
            saveUserDataInSharedPreferences()
        }
    }

    private fun saveUserDataInSharedPreferences() {
        userStorage.save(userData!!)
    }

    private fun signOut() {
        userStorage.clear()

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