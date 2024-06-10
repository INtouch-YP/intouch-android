package care.intouch.app.feature.profile.presentation.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import care.intouch.app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private var _state = MutableStateFlow(loadProfileData())
    val state = _state.asStateFlow()
    private var _viewsState = MutableStateFlow(getDefaultViewState())
    val viewsState = _viewsState.asStateFlow()


    fun updateName(name: String) {
        if (name.length <= MAX_NAME_LENGTH) {
            val isTextValid = isTextValid(name)
            val isNameValid = isTextValid && (name.length > 2)
            var errorMessage = ""
            if (!isTextValid) {
                errorMessage += context.getString(R.string.profile_invalid_char_error) + "\n"
            }
            if (name.length <= 2) {
                errorMessage += context.getString(R.string.profile_small_name_error) + "\n"
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isNameValid,
                    name = ProfileInformationData(name, isNameValid),
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    errorMessage.trim()
                )
            }
        }
    }

    fun updateLastName(lastName: String) {
        if (lastName.length <= MAX_NAME_LENGTH) {
            val isTextValid = isTextValid(lastName)
            val isLastNameValid = isTextValid && (lastName.length > 2)
            var errorMessage = ""
            if (!isTextValid) {
                errorMessage += context.getString(R.string.profile_invalid_char_error) + "\n"
            }
            if (lastName.length <= 2) {
                errorMessage += context.getString(R.string.profile_small_last_name_error) + "\n"
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isLastNameValid,
                    name = _state.value.name,
                    lastName = ProfileInformationData(lastName, isLastNameValid),
                    email = _state.value.email,
                    errorMessage.trim()
                )
            }
        }
    }

    fun updateEmail(email: String) {
        if(email.length <= MAX_EMAIL_LENGTH) {
            val isEmailValid = isEmailValid(email)
            var errorMessage = ""
            if (!isEmailValid) {
                errorMessage += context.getString(R.string.email_not_valid_error)
            }
            _state.update {
                ResultOfCheckProfileData(
                    dataIsValid = isEmailValid,
                    name = _state.value.name,
                    lastName = _state.value.lastName,
                    email = ProfileInformationData(email, isEmailValid),
                    errorMessage
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
            name = ProfileInformationData("MyName", true),
            lastName = ProfileInformationData("MyLastName", true),
            email = ProfileInformationData("gogo@gmail.com", true),
            errorMessage = ""
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