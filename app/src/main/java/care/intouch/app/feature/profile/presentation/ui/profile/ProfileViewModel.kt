package care.intouch.app.feature.profile.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    //private var _state = MutableStateFlow(ResultOfCheckProfileData())
    private var _state = MutableStateFlow(
        ResultOfCheckProfileData(
            dataIsValid = true,
            name = "MyName",
            lastName = "MyLastName",
            email = "gogo@gmail.com",
            message = ""
        )
    )
    val state = _state.asStateFlow()

    private fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex("[a-zA-Z- \\.]*")
        return regex.matches(text)
    }

    fun updateState(name: String, lastName: String, email: String) {
        val isEmailValid = isEmailValid(email)
        val isNameValid = isTextValid(name)
        val isLastNameValid = isTextValid(lastName)
        val allDataIsValid = isEmailValid && isNameValid && isLastNameValid && (name.length > 2) && (lastName.length > 2)
        var message = ""
        if (!isEmailValid) {
            message += "Not a valid e-mail address." + "\n"
        }
        if (!isNameValid || !isLastNameValid) {
            message += "Invalid characters. Only letters, spaces, and periods are allowed." + "\n"
        }
        if (name.length <= 2) {
            message += "Please enter a name with at least 2 characters." + "\n"
        }
        if (lastName.length <= 2) {
            message += "Please enter a last name with at least 2 characters." + "\n"
        }

        if (allDataIsValid) {
            message = "You have successfully changed your name"
        }
        _state.value = ResultOfCheckProfileData(
            allDataIsValid,
            name,
            lastName,
            email,
            message.trim()
        )
        Log.d("PROFILE_SCREEN_TAG", "Name = $name AllDataIsValid = (${_state.value.dataIsValid})")
    }

    fun sendDataInDomain() {

    }


}


//<string name="email_small_name_error">Please enter a name with at least 2 characters.</string>
//<string name="email_not_valid_error">Not a valid e-mail address</string>
//<string name="email_invalid_char_error">Invalid characters. Only letters, spaces, and periods are allowed.</string>
//<string name="info_about_change_name">You have successfully changed your name</string>