package care.intouch.app.feature.authorization.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.core.utils.BLANC_STRING
import care.intouch.app.feature.authorization.domain.useCase.ResetPasswordUseCase
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryEvent
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordRecoveryScreenState())
    val uiState: StateFlow<PasswordRecoveryScreenState> = _uiState

    fun onEvent(event: PasswordRecoveryEvent) {
        when(event) {
            is PasswordRecoveryEvent.OnPasswordRecovery -> resetPassword(event.email)
            is PasswordRecoveryEvent.ResetUiState -> resetUiState()
        }
    }

    private fun resetPassword(email: String) {
        viewModelScope.launch {
            val result = resetPasswordUseCase(email)

            when {
                result.isSuccess -> {
                    _uiState.update { passwordRecoveryScreenState ->
                        passwordRecoveryScreenState.copy(isSuccess = true)
                    }
                }
                result.isFailure -> {
                    val response = result.exceptionOrNull()
                    _uiState.update { passwordRecoveryScreenState ->
                        passwordRecoveryScreenState.copy(errorMessage = "Error: ${response?.message}")
                    }
                }
            }
        }
    }

    private fun resetUiState() {
        _uiState.update { passwordRecoveryScreenState ->
            passwordRecoveryScreenState.copy(
                errorMessage = BLANC_STRING,
                isSuccess = false
            )
        }
    }
}