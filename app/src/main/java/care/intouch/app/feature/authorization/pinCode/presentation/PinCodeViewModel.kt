package care.intouch.app.feature.authorization.pinCode.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.pinCode.data.PinCodeState
import care.intouch.app.feature.authorization.pinCode.domain.InstallPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.IsSetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.ResetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.VerifyPinCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val installPinCodeUseCase: InstallPinCodeUseCase,
    private val verifyPinCodeUseCase: VerifyPinCodeUseCase,
    private val isSetPinCodeUseCase: IsSetPinCodeUseCase,
    private val resetPinCodeUseCase: ResetPinCodeUseCase
) : ViewModel() {

    @SuppressLint("LogNotTimber")
    fun init() {
        viewModelScope.launch(Dispatchers.IO) {

            Log.d("TAG", "Проверка установлен ли пин код до ")
            testLog(isSetPinCodeUseCase.invoke())

            installPinCodeUseCase.invoke("1234")

            Log.d("TAG", "До Подтверждения Проверка установлен ли пин код после ")
            testLog(isSetPinCodeUseCase.invoke())

            Log.d("TAG", "Подтверждение")
            testLog(installPinCodeUseCase.invoke("1234"))


            Log.d("TAG", "После Подтверждения Проверка установлен ли пин код после ")
            testLog(isSetPinCodeUseCase.invoke())

            Log.d("TAG", "Проверка подтверждения: Правильный ")
            testLog(verifyPinCodeUseCase.invoke("1234"))


            Log.d("TAG", "Проверка подтверждения: Неправильный ")
            testLog(verifyPinCodeUseCase.invoke("1214"))
            resetPinCodeUseCase.invoke()
        }

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