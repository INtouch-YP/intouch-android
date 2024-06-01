package care.intouch.app.feature.authorization.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private var _resetPasswordState = MutableStateFlow("")
    val resetPasswordState = _resetPasswordState.asStateFlow()

    fun resetPassword(email: String) {
        viewModelScope.launch {
            val result = resetPasswordUseCase(email)

            when {
                result.isSuccess -> {
                    val response = result.getOrNull()
                    Timber
                        .tag("VM PASS RECOVERY")
                        .d("Success ${response?.message}")
                }
                result.isFailure -> {
                    val response = result.exceptionOrNull()
                    Timber
                        .tag("VM PASS RECOVERY")
                        .d("Error ${response?.message}")
                }
            }
        }
    }
}