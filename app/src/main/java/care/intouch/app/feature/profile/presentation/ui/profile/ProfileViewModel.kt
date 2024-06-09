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


    fun updateName(name: String){
        val isTextValid = isTextValid(name)
        val isNameValid = isTextValid && (name.length > 2)
        var errorMessage = ""
        if (!isTextValid) {
            errorMessage += "Invalid characters. Only letters, spaces,\nand periods are allowed." + "\n"
        }
        if (name.length <= 2) {
            errorMessage += "Please enter a name with at least 2 characters." + "\n"
        }
        _state.value = ResultOfCheckProfileData(
            dataIsValid = isNameValid,
            name = ProfileInformationData(name, isNameValid),
            lastName = _state.value.lastName,
            email = _state.value.email,
            errorMessage.trim()
        )
    }

    fun updateLastName(lastName: String){
        val isTextValid = isTextValid(lastName)
        val isLastNameValid = isTextValid && (lastName.length > 2)
        var errorMessage = ""
        if (!isTextValid) {
            errorMessage += "Invalid characters. Only letters, spaces,\nand periods are allowed." + "\n"
        }
        if (lastName.length <= 2) {
            errorMessage += "Please enter a name with at least 2 characters." + "\n"
        }
        _state.value = ResultOfCheckProfileData(
            dataIsValid = isLastNameValid,
            name = _state.value.name,
            lastName = ProfileInformationData(lastName, isLastNameValid),
            email = _state.value.email,
            errorMessage.trim()
        )
    }

    fun updateEmail(email: String){
        val isEmailValid = isEmailValid(email)
        var errorMessage = ""
        if (!isEmailValid) {
            errorMessage += "Not a valid e-mail address."
        }
        _state.value = ResultOfCheckProfileData(
            dataIsValid = isEmailValid,
            name = _state.value.name,
            lastName = _state.value.lastName,
            email = ProfileInformationData(email, isEmailValid),
            errorMessage
        )
    }



//    fun updateState(name: String, lastName: String, email: String) {    //разбить на 3 метода в конце каждого сделать обновление состояния
//        val isEmailValid = isEmailValid(email)
//        val isNameValid = isTextValid(name)
//        val isLastNameValid = isTextValid(lastName)
//        val allDataIsValid = isEmailValid && isNameValid && isLastNameValid && (name.length > 2) && (lastName.length > 2)
//        var errorMessage = ""
//        if (!isEmailValid) {
//            errorMessage += "Not a valid e-mail address." + "\n"
//        }
//        if (!isNameValid || !isLastNameValid) {
//            errorMessage += "Invalid characters. Only letters, spaces,\nand periods are allowed." + "\n"
//        }
//        if (name.length <= 2) {
//            errorMessage += "Please enter a name with at least 2 characters." + "\n"
//        }
//        if (lastName.length <= 2) {
//            errorMessage += "Please enter a last name with at least 2 characters." + "\n"
//        }
//
//        _state.value = ResultOfCheckProfileData(
//            allDataIsValid,
//            name,
//            lastName,
//            email,
//            errorMessage.trim()
//        )
//    }

    fun sendDataInDomain() {

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
}