package care.intouch.app.feature.authorization.pinCode.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.InstallationPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.ResetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.VerificationPinCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val installationPinCodeUseCase: InstallationPinCodeUseCase,
    private val verificationPinCodeUseCase: VerificationPinCodeUseCase,
    val resetPinCodeUseCase: ResetPinCodeUseCase
): ViewModel() {

    fun init(){
        installationPinCodeUseCase.invoke("1234")

        when(val w = verificationPinCodeUseCase.invoke("1234")){
            is Result.Success -> {
                Log.d("TAG","Правильный " +  w.data.toString())
            }
            is Result.Error -> {
                Log.d("TAG","Правильный " + w.exception.toString())
            }
        }

        when(val w = verificationPinCodeUseCase.invoke("1214")){
            is Result.Success -> {
                Log.d("TAG","НеПравильный " +  w.data.toString())
            }
            is Result.Error -> {
                Log.d("TAG","НеПравильный " + w.exception.toString())
            }
        }

        installationPinCodeUseCase.invoke("1134")

        when(val w = verificationPinCodeUseCase.invoke("1234")){
            is Result.Success -> {
                Log.d("TAG","Правильный второй " +  w.data.toString())
            }
            is Result.Error -> {
                Log.d("TAG","Правильный второй " + w.exception.toString())
            }
        }
    }
}