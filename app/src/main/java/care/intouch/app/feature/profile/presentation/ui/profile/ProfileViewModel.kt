package care.intouch.app.feature.profile.presentation.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableStateFlow( loadProfileData() )
    val state = _state.asStateFlow()

    fun updateState(name: String, lastName: String, email: String) {    //разбить на 3 метода в конце каждого сделать обновление состояния
        val isEmailValid = isEmailValid(email)
        val isNameValid = isTextValid(name)
        val isLastNameValid = isTextValid(lastName)
        val allDataIsValid = isEmailValid && isNameValid && isLastNameValid && (name.length > 2) && (lastName.length > 2)
        var errorMessage = ""
        if (!isEmailValid) {
            errorMessage += "Not a valid e-mail address." + "\n"
        }
        if (!isNameValid || !isLastNameValid) {
            errorMessage += "Invalid characters. Only letters, spaces, and periods are allowed." + "\n"
        }
        if (name.length <= 2) {
            errorMessage += "Please enter a name with at least 2 characters." + "\n"
        }
        if (lastName.length <= 2) {
            errorMessage += "Please enter a last name with at least 2 characters." + "\n"
        }

        _state.value = ResultOfCheckProfileData(
            allDataIsValid,
            name,
            lastName,
            email,
            errorMessage.trim()
        )
    }

    fun sendDataInDomain() {

    }

    private fun loadProfileData(): ResultOfCheckProfileData {
        return ResultOfCheckProfileData(
            dataIsValid = true,
            name = "MyName",
            lastName = "MyLastName",
            email = "gogo@gmail.com",
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
}