package care.intouch.app.feature.authorization.pinCode.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.pinCode.data.PinCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    @SuppressLint("LogNotTimber")
    fun init() {
        viewModelScope.launch(Dispatchers.IO) {

            Log.d("TAG", "Проверка установлен ли пин код до ")
            testLog(repository.isSetPinCode())

            repository.installPinCode("1234")

            Log.d("TAG", "До Подтверждения Проверка установлен ли пин код после ")
            testLog(repository.isSetPinCode())

            Log.d("TAG", "Подтверждение")
            testLog(repository.installPinCode("1234"))


            Log.d("TAG", "После Подтверждения Проверка установлен ли пин код после ")
            testLog(repository.isSetPinCode())

            Log.d("TAG", "Проверка подтверждения: Правильный ")
            testLog(repository.verifyPinCode("1234"))


            Log.d("TAG", "Проверка подтверждения: Неправильный ")
            testLog(repository.verifyPinCode("1214"))
            repository.resetPinCode()
        }

    }

    @SuppressLint("LogNotTimber")
    private fun testLog(value: Result<Boolean>) {
        Log.d("TAG", value.toString())
    }

    @SuppressLint("LogNotTimber")
    private fun testLog(value: PinCodeState) {
        when (value) {
            PinCodeState.Confirmed -> Log.d("TAG", "Confirmed")
            is PinCodeState.Error -> Log.d("TAG", "Error")
            PinCodeState.IncorrectPinCode -> Log.d("TAG", "IncorrectPinCode")
            PinCodeState.Installed -> Log.d("TAG", "Installed")
            PinCodeState.NotInstalled -> Log.d("TAG", "NotInstalled")
            PinCodeState.Removed -> Log.d("TAG", "Removed")
            PinCodeState.Skipped -> Log.d("TAG", "Skipped")
            PinCodeState.AlmostInstalled -> Log.d("TAG", "AlmostInstalled")
        }

    }
}