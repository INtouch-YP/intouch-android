package care.intouch.app.feature.authorization.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.ResetPasswordUseCase
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryEvent
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoverySideEffect
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
            is PasswordRecoveryEvent.OnPasswordRecovery -> recoverPassword(event.email)
            is PasswordRecoveryEvent.OnTextFieldChange -> onTextFieldChange(event.text)
        }
    }

    private fun recoverPassword(email: String) {
        viewModelScope.launch {
            val passwordRecoveryResult = resetPasswordUseCase(email)

            when {
                passwordRecoveryResult.isSuccess -> {
                    _sideEffect.emit(PasswordRecoverySideEffect.Success)
                }

                passwordRecoveryResult.isFailure -> {
                    val passwordRecoveryError = passwordRecoveryResult.exceptionOrNull()

                    if (passwordRecoveryError?.message == USER_NOT_EXIST) {
                        _sideEffect.emit(PasswordRecoverySideEffect.UserNotExist)
                    } else {
                        _uiState.update { passwordRecoveryScreenState ->
                            passwordRecoveryScreenState.copy(
                                recoveryErrorMessage = "${passwordRecoveryError?.message}",
                            )
                        }
                        _sideEffect.emit(PasswordRecoverySideEffect.Failure)
                    }
                }
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

    private  fun isEmailValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    companion object {
        const val USER_NOT_EXIST = "User with this email does not exist."
    }
}