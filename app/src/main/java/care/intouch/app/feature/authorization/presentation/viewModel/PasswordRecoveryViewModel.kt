package care.intouch.app.feature.authorization.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.authorization.domain.useCase.ResetPasswordUseCase
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryEvent
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoverySideEffect
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordRecoveryScreenState())
    val uiState: StateFlow<PasswordRecoveryScreenState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PasswordRecoverySideEffect>()
    val sideEffect: SharedFlow<PasswordRecoverySideEffect> = _sideEffect.asSharedFlow()

    fun onEvent(event: PasswordRecoveryEvent) {
        when (event) {
            is PasswordRecoveryEvent.OnPasswordRecovery -> recoverPassword(uiState.value.textFieldValue)
            is PasswordRecoveryEvent.OnTextFieldChange -> onTextFieldChange(event.text)
        }
    }

    private fun recoverPassword(email: String) {
        viewModelScope.launch {
            resetPasswordUseCase(email)
                .onSuccess {
                    handleRecoveryPasswordSuccess()
                }
                .onFailure { throwable ->
                    handleRecoveryPasswordFailure(throwable)
                }
        }
    }

    private fun onTextFieldChange(text: String) {
        if (text.isNotEmpty()) {
            if (isEmailValid(text = text)) {
                _uiState.update { passwordRecoveryScreenState ->
                    passwordRecoveryScreenState.copy(
                        textFieldValue = text,
                        enableButton = true,
                        isVisibleCaption = false
                    )
                }
            } else {
                _uiState.update { passwordRecoveryScreenState ->
                    passwordRecoveryScreenState.copy(
                        textFieldValue = text,
                        enableButton = false,
                        isVisibleCaption = true
                    )
                }
            }
        } else {
            _uiState.update { passwordRecoveryScreenState ->
                passwordRecoveryScreenState.copy(
                    textFieldValue = text,
                    enableButton = false,
                    isVisibleCaption = false
                )
            }
        }

    }

    private suspend fun handleRecoveryPasswordSuccess() {
        _sideEffect.emit(PasswordRecoverySideEffect.NavigateToPasswordSendInformation)
    }

    private suspend fun handleRecoveryPasswordFailure(throwable: Throwable) {
        when (throwable) {
            is NetworkException.BadRequest -> {
                _sideEffect.emit(PasswordRecoverySideEffect.NavigateToPasswordSendInformation)
            }
            else -> {
                val message = throwable.message
                val errorMessage = if (message.isNullOrEmpty()) {
                    StringVO.Resource(R.string.unknown_error)
                } else {
                    StringVO.Plain(message)
                }
                _sideEffect.emit(PasswordRecoverySideEffect.ShowToast(message = errorMessage))
            }
        }
    }

    private  fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}