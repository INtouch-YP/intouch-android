package care.intouch.app.feature.profile.presentation.ui.profile

import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {



    private fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    private fun isTextValid(text: String): Boolean {
        val regex = Regex("[a-zA-Z- \\.]*")
        return regex.matches(text) && (text.length > 2)
    }

    fun checkProfileData(name: String, lastName: String, email: String): ResultOfCheckProfileData{
        val isEmailValid = isEmailValid(email)
        val isNameValid = isTextValid(name)
        val isLastNameValid = isTextValid(lastName)
        var message = ""
        if (!isEmailValid) {
            message += "Not a valid e-mail address." + "\n"
        }
        if (!isNameValid || !isLastNameValid) {
            message += "Invalid characters. Only letters, spaces, and periods are allowed." + "\n"
        }
        if (name.length <=2){
            message += "Please enter a name with at least 2 characters." + "\n"
        }
        if (lastName.length <=2){
            message += "Please enter a last name with at least 2 characters." + "\n"
        }

        if (isEmailValid && isNameValid && isLastNameValid){
            message = "You have successfully changed your name"
        }
        return ResultOfCheckProfileData(isEmailValid && isNameValid && isLastNameValid, message.trim())
    }

    fun sendDataInDomain(){

    }
}


//<string name="email_small_name_error">Please enter a name with at least 2 characters.</string>
//<string name="email_not_valid_error">Not a valid e-mail address</string>
//<string name="email_invalid_char_error">Invalid characters. Only letters, spaces, and periods are allowed.</string>
//<string name="info_about_change_name">You have successfully changed your name</string>