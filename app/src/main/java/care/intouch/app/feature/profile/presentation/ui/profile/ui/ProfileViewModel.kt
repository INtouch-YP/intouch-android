package care.intouch.app.feature.profile.presentation.ui.profile.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeUseCase
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserDataUseCase
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserEmailUseCase
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileDataEvent
import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileState
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
    private val updateUserEmailUseCase: UpdateUserEmailUseCase,
    private val confirmEmailChangeUseCase: ConfirmEmailChangeUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()
    private var userDataFromSharedPref: User? = null
    private var currentProfileData: ProfileData = ProfileData("", "")
    private var currentEmail: String = ""
    private var updateEmailFlag = true

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
                )
            }

            is ProfileDataEvent.OnEditLastNameButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton
                )
            }

            is ProfileDataEvent.OnEditNameButtonClick -> {
                changeTextFieldsAndButtonsEnabled(
                    name = event.name,
                    lastName = event.lastName,
                    email = event.email,
                    saveChangesButton = event.saveChangesButton
                )
            }

            is ProfileDataEvent.OnSaveChangesButtonClick -> {
                if (
                    currentProfileData.firstName != userDataFromSharedPref!!.firstName ||
                    currentProfileData.lastName != userDataFromSharedPref!!.lastName
                ) {
                    updateUserData(event)
                }
                if (currentEmail != userDataFromSharedPref!!.email) {
                    updateUserEmail(event)
                }
            }

            is ProfileDataEvent.OnSingOutButtonClick -> {
                signOut()
            }

            is ProfileDataEvent.onConfirmEmailUpdate -> {
                confirmEmailUpdate(event.id, event.token)
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
                _state.value.copy(
                    dataIsValid = isNameValid,
                    name = StringVO.Plain(name),
                    nameIsValid = isNameValid,
                    dataIsNotValidMessage = errorMessage,
                    emailResponseIsSuccess = false,
                    nameResponseHasBeenReceived = false,
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
                _state.value.copy(
                    dataIsValid = isLastNameValid,
                    lastName = StringVO.Plain(lastName),
                    lastNameIsValid = isLastNameValid,
                    dataIsNotValidMessage = errorMessage,
                    emailResponseIsSuccess = false,
                    nameResponseHasBeenReceived = false,
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
            _state.value.copy(
                dataIsValid = isEmailValid,
                email = StringVO.Plain(email),
                emailIsValid = isEmailValid,
                dataIsNotValidMessage = errorMessage,
                emailResponseIsSuccess = false,
                nameResponseHasBeenReceived = false,
            )
        }
    }

    private fun readUserDataFromSharedPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataFromSharedPreferences: User? = userStorage.read()
            if (dataFromSharedPreferences != null) {
                _state.update {
                    _state.value.copy(
                        dataIsValid = true,
                        name = StringVO.Plain(dataFromSharedPreferences.firstName),
                        nameIsValid = true,
                        lastName = StringVO.Plain(dataFromSharedPreferences.lastName),
                        lastNameIsValid = true,
                        email = StringVO.Plain(dataFromSharedPreferences.email),
                        emailIsValid = true
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
    }

    private fun signOut() {
        userStorage.clear()
    }

    private fun changeTextFieldsAndButtonsEnabled(
        name: Boolean,
        lastName: Boolean,
        email: Boolean,
        saveChangesButton: Boolean
    ) {
        _state.update {
            _state.value.copy(
                saveChangesButtonVisibility = saveChangesButton,
                nameTextFieldEnabled = name,
                lastNameTextFieldEnabled = lastName,
                emailTextFieldEnabled = email,
                nameButtonEnabled = !name,
                lastNameButtonEnabled = !lastName,
                emailButtonEnabled = !email,
                emailChangeDeepLinkRequestSent = false,
            )
        }
    }

    private fun isEmailValid(text: String): Boolean {
        val regex = Regex(REGEX_EMAIL_ADDRESS)
        return regex.matches(text)
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex(NAME_REGEX)
        return regex.matches(text)
    }

    private fun updateUserEmail(event: ProfileDataEvent.OnSaveChangesButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUserEmailUseCase.invoke(currentEmail)
                .onSuccess {
                    updateStateWhenEmailOnSuccess(StringVO.Plain(it.message))
                }.onFailure { error ->
                    when (error) {
                        is NetworkException.BadRequest -> {
                            val message = if (error.message.isNullOrEmpty()) {
                                StringVO.Resource(R.string.unknown_error)
                            } else {
                                StringVO.Plain(error.message!!)
                            }
                            updateStateWhenUserEmailOnError(message)
                        }

                        is NetworkException.NoInternetConnection -> {
                            updateStateWhenUserEmailOnError(StringVO.Resource(R.string.problem_with_connection))
                        }

                        else -> {
                            val message = if (error.message.isNullOrEmpty()) {
                                StringVO.Resource(R.string.unknown_error)
                            } else {
                                StringVO.Plain(error.message!!)
                            }
                            updateStateWhenUserEmailOnError(message)
                        }
                    }
                }
        }
        changeTextFieldsAndButtonsEnabled(
            name = event.name,
            lastName = event.lastName,
            email = event.email,
            saveChangesButton = event.saveChangesButton
        )
    }

    private fun updateUserData(event: ProfileDataEvent.OnSaveChangesButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {

            updateUserDataUseCase.invoke(currentProfileData, userDataFromSharedPref!!.id)
                .onSuccess {
                    updateStateWhenUserDataOnSuccess(StringVO.Resource(R.string.info_about_change_profile_data))
                }.onFailure { error ->
                    when (error) {
                        is NetworkException.BadRequest -> {
                            updateStateWhenUserDataOnError(StringVO.Resource(R.string.unknown_error))
                        }

                        is NetworkException.NoInternetConnection -> {
                            updateStateWhenUserDataOnError(StringVO.Resource(R.string.problem_with_connection))
                        }

                        else -> {
                            updateStateWhenUserDataOnError(StringVO.Resource(R.string.unknown_error))
                        }
                    }
                }
        }
        changeTextFieldsAndButtonsEnabled(
            name = event.name,
            lastName = event.lastName,
            email = event.email,
            saveChangesButton = event.saveChangesButton
        )
    }

    private suspend fun saveUserDataInSharedPreferences() {
        val emailToSave: String =
            if (_state.value.emailResponseIsSuccess && _state.value.emailColorMessageIsGreenOrRed) {
                currentEmail
            } else {
                userDataFromSharedPref!!.email
            }
        userStorage.save(
            User(
                id = userDataFromSharedPref!!.id,
                firstName = currentProfileData.firstName,
                lastName = currentProfileData.lastName,
                email = emailToSave,
                acceptPolicy = userDataFromSharedPref!!.acceptPolicy,
                newEmailChanging = userDataFromSharedPref!!.newEmailChanging,
                newEmailTemp = userDataFromSharedPref!!.newEmailTemp
            )
        )
        userDataFromSharedPref = userDataFromSharedPref!!.copy(
            firstName = currentProfileData.firstName,
            lastName = currentProfileData.lastName,
            email = emailToSave
        )
    }

    private suspend fun updateStateWhenEmailOnSuccess(message: StringVO) {

        _state.update {
            _state.value.copy(
                resultMessageOfChangeEmailRequest = message,
                emailResponseIsSuccess = true,
                emailColorMessageIsGreenOrRed = true,
            )
        }
        saveUserDataInSharedPreferences()
    }

    private suspend fun updateStateWhenUserDataOnSuccess(message: StringVO) {

        _state.update {
            _state.value.copy(
                resultMessageOfChangeNameRequest = message,
                nameResponseIsSuccess = true,
                nameResponseHasBeenReceived = true
            )
        }
        saveUserDataInSharedPreferences()
    }

    private fun updateStateWhenUserEmailOnError(message: StringVO) {
        _state.update {
            _state.value.copy(
                resultMessageOfChangeEmailRequest = message,
                emailColorMessageIsGreenOrRed = false,
                emailResponseIsSuccess = true
            )
        }

    }

    private fun updateStateWhenUserDataOnError(message: StringVO) {
        _state.update {
            _state.value.copy(
                resultMessageOfChangeNameRequest = message,
                nameResponseIsSuccess = false,
                nameResponseHasBeenReceived = true
            )
        }
    }

    private fun confirmEmailUpdate(id: String?, token: String?) {
        if (id != null && token != null && updateEmailFlag) {
            viewModelScope.launch(Dispatchers.IO) {
                confirmEmailChangeUseCase.invoke(id, token)
                    .onSuccess {
                        if(it.message != null) {
                            val message = it.message
                            _state.update {
                                _state.value.copy(
                                    emailChangeDeepLinkRequestSent = true,
                                    emailChangeDeepLinkRequestSentIsSuccess = true,
                                    emailChangeDeepLinkRequestMessage = StringVO.Plain(message)
                                )
                            }
                        } else if (it.error != null) {
                            val message = it.error
                            _state.update {
                                _state.value.copy(
                                    emailChangeDeepLinkRequestSent = true,
                                    emailChangeDeepLinkRequestSentIsSuccess = false,
                                    emailChangeDeepLinkRequestMessage = StringVO.Plain(message)
                                )
                            }
                        }

                    }
                    .onFailure {
                        _state.update {
                            _state.value.copy(
                                emailChangeDeepLinkRequestSent = true,
                                emailChangeDeepLinkRequestSentIsSuccess = false,
                                emailChangeDeepLinkRequestMessage = StringVO.Resource(R.string.unknown_error)
                            )
                        }
                    }
            }
            updateEmailFlag = false
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