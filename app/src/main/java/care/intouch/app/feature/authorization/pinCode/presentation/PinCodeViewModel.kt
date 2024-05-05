package care.intouch.app.feature.authorization.pinCode.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.InstallPinCodeUseCase
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
    val resetPinCodeUseCase: ResetPinCodeUseCase
): ViewModel() {

    fun init(){
        viewModelScope.launch(Dispatchers.IO) {
            installPinCodeUseCase.invoke("1234")

            when(val w = verifyPinCodeUseCase.invoke("1234")){
                is Result.Success -> {
                    Log.d("TAG","Правильный " +  w.data.toString())
                }
                is Result.Error -> {
                    Log.d("TAG","Правильный " + w.exception.toString())
                }
            }

            when(val w = verifyPinCodeUseCase.invoke("1214")){
                is Result.Success -> {
                    Log.d("TAG","НеПравильный " +  w.data.toString())
                }
                is Result.Error -> {
                    Log.d("TAG","НеПравильный " + w.exception.toString())
                }
            }

            installPinCodeUseCase.invoke("1134")

            when(val w = verifyPinCodeUseCase.invoke("1234")){
                is Result.Success -> {
                    Log.d("TAG","Правильный второй " +  w.data.toString())
                }
                is Result.Error -> {
                    Log.d("TAG","Правильный второй " + w.exception.toString())
                }
            }
        }

    }
}