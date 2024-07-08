package care.intouch.app.feature.profile.presentation.ui.profile.ui

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserDataUseCase
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserEmailUseCase
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataEvent
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileInformationData
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataState
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileState
import care.intouch.app.feature.profile.presentation.ui.profile.models.ViewsComponentsState
import care.intouch.app.feature.profile.presentation.ui.profile.string_extension.replaceChars
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userStorage: UserStorage,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val updateUserEmailUseCase: UpdateUserEmailUseCase
) : ViewModel() {

    private var _state =
        MutableStateFlow(ProfileState(ProfileDataState(), ViewsComponentsState()))

    //    private var _state =
//        MutableStateFlow(ProfileState(getDefaultProfileData(), ViewsComponentsState()))
    val state = _state.asStateFlow()
    private var userDataFromSharedPref: User? = null      //
    private var currentProfileData: ProfileData = ProfileData("", "")
    private var currentEmail: String = ""

    init {
        readUserDataFromSharedPreferences()
    }

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
                if (
                    currentProfileData.firstName != userDataFromSharedPref!!.firstName ||
                    currentProfileData.lastName != userDataFromSharedPref!!.lastName
                ) {
                    Log.d("MY_INTOUCH_TAG", "OnSaveChangesButtonClick name or lastname not same")
                    updateUserData(event)
                }
                if (currentEmail != userDataFromSharedPref!!.email) {
                    Log.d("MY_INTOUCH_TAG", "OnSaveChangesButtonClick email not same")
                    updateUserEmail(event)
                }
            }

            is ProfileDataEvent.OnSingOutButtonClick -> {
                signOut()
            }
        }
    }

    private fun updateName(event: ProfileDataEvent.OnName) {
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
            currentProfileData = currentProfileData.copy(
                firstName = event.name
            )
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

    private fun updateLastName(event: ProfileDataEvent.OnLastName) {
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
            currentProfileData = currentProfileData.copy(
                lastName = event.lastName
            )
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

    private fun updateEmail(event: ProfileDataEvent.OnEmail) {
        val email = event.email
        val isEmailValid = isEmailValid(email)
        var errorMessage: StringVO = StringVO.Plain("")
        if (!isEmailValid) {
            errorMessage = event.errorEmailNotValid
        }
        currentEmail = event.email
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

    private fun readUserDataFromSharedPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataFromSharedPreferences: User = userStorage.read()
            _state.update {
                ProfileState(
                    profileDataState = ProfileDataState(
                        dataIsValid = true,
                        name = ProfileInformationData(
                            StringVO.Plain(dataFromSharedPreferences.firstName),
                            true
                        ),
                        lastName = ProfileInformationData(
                            StringVO.Plain(dataFromSharedPreferences.lastName),
                            true
                        ),
                        email = ProfileInformationData(
                            StringVO.Plain(dataFromSharedPreferences.email),
                            true
                        ),
                        errorMessage = _state.value.profileDataState.errorMessage,
                        successMessage = _state.value.profileDataState.successMessage
                    ),
                    viewsComponentsState = _state.value.viewsComponentsState
                )
            }
            currentProfileData = ProfileData(
                firstName = dataFromSharedPreferences.firstName,
                lastName = dataFromSharedPreferences.lastName
            )
            currentEmail = dataFromSharedPreferences.email
            userDataFromSharedPref = dataFromSharedPreferences
        }
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

//    private fun getDefaultProfileData(): ProfileDataState {
//        return ProfileDataState(
//            dataIsValid = true,
//            name = ProfileInformationData(StringVO.Plain("MyName"), true),
//            lastName = ProfileInformationData(StringVO.Plain("MyLastName"), true),
//            email = ProfileInformationData(StringVO.Plain("gogo@gmail.com"), true),
//            errorMessage = StringVO.Plain(""),
//            successMessage = StringVO.Resource(R.string.info_about_change_profile_data)
//        )
//    }

    private fun isEmailValid(text: String): Boolean {
        val regex = Regex(REGEX_EMAIL_ADDRESS)
        return regex.matches(text)
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex(NAME_REGEX)
        return regex.matches(text)
    }

//    private fun userDataVerification() {
//
//    }

    private fun updateUserEmail(event: ProfileDataEvent.OnSaveChangesButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUserEmailUseCase.invoke(currentEmail)
                .onSuccess { //обработать данные
                    Log.d("MY_INTOUCH_TAG", "Message Email SUCCESS - ${it.message}")
                    updateUserDataAndEmailOnSuccess(event, StringVO.Plain(it.message))
                }.onFailure { error ->
                    when (error) {
                        is NetworkException.BadRequest -> {
                            Log.d("MY_INTOUCH_TAG", "Message Email BadRequest - ${error.message}")
                            updateUserDataAndEmailOnError(StringVO.Plain(error.message?: "User with this email is already exists111"))
                        }

                        is NetworkException.NoInternetConnection -> {
                            Log.d(
                                "MY_INTOUCH_TAG",
                                "Message Email NoInternetConnection - ${error.message}"
                            )
                            updateUserDataAndEmailOnError(StringVO.Plain(error.message?: "No internet connection111"))
                        }

                        else -> {
                            // ошибка по дефолту
                            Log.d("MY_INTOUCH_TAG", "Message Email Error - ${error.message}")
                            updateUserDataAndEmailOnError(StringVO.Plain(error.message?: "Unknown error"))
                        }
                    }
                }
        }
    }

    private fun updateUserData(event: ProfileDataEvent.OnSaveChangesButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUserDataUseCase.invoke(currentProfileData, userDataFromSharedPref!!.id)
                .onSuccess {
                    Сделай логи!!
                    updateUserDataAndEmailOnSuccess(event, StringVO.Resource(R.string.info_about_change_profile_data))
                }.onFailure { error ->
                    when (error) {
                        is NetworkException.BadRequest -> {
                            updateUserDataAndEmailOnError(StringVO.Resource(R.string.unknown_error))
                        }

                        is NetworkException.NoInternetConnection -> {
                            updateUserDataAndEmailOnError(StringVO.Resource(R.string.problem_with_connection))
                        }

                        else -> {
                            updateUserDataAndEmailOnError(StringVO.Resource(R.string.problem_with_connection))
                        }
                    }
                }
        }
    }

    private fun saveUserDataInSharedPreferences() {
        userStorage.save(
            User(
                id = userDataFromSharedPref!!.id,
                firstName = currentProfileData.firstName,
                lastName = currentProfileData.lastName,
                email = currentEmail,
                acceptPolicy = userDataFromSharedPref!!.acceptPolicy,
                newEmailChanging = userDataFromSharedPref!!.newEmailChanging,
                newEmailTemp = userDataFromSharedPref!!.newEmailTemp
            )
        )
        userDataFromSharedPref = userDataFromSharedPref!!.copy(
            firstName = currentProfileData.firstName,
            lastName = currentProfileData.lastName,
            email = currentEmail
        )
    }

    private fun updateUserDataAndEmailOnSuccess(event: ProfileDataEvent.OnSaveChangesButtonClick, message: StringVO) {
        saveUserDataInSharedPreferences() // Тут может быть проблема ибо этот же метод вызывается в updateUserEmail при .onSuccess
        _state.update {
            ProfileState(
                profileDataState = _state.value.profileDataState.copy(
                    successMessage = message
                ),
                viewsComponentsState = _state.value.viewsComponentsState.copy(
                    colorOfMessageIsGreenOrRed = true
                )
            )
        }
        changeTextFieldsAndButtonsEnabled(
            name = event.name,
            lastName = event.lastName,
            email = event.email,
            saveChangesButton = event.saveChangesButton,
            infIsUpdate = event.infIsUpdate,
        )
    }

    private fun updateUserDataAndEmailOnError(message: StringVO) {
        _state.update {
            ProfileState(
                profileDataState = _state.value.profileDataState.copy(
                    errorMessage = message
                ),
                viewsComponentsState = _state.value.viewsComponentsState.copy(
                    informationIsUpdate = true,
                    colorOfMessageIsGreenOrRed = false
                )
            )
        }
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