package care.intouch.app.feature.profile.presentation.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    private val _textName: MutableLiveData<String> = MutableLiveData("")
    fun setTextName(name: String) {
        _textName.value = name
    }


    var textLastName: String = ""
    var textEmail: String = ""

    private fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }



}