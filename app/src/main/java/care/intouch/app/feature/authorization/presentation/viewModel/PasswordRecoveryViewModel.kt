package care.intouch.app.feature.authorization.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.core.utils.BLANC_STRING
import care.intouch.app.feature.authorization.domain.useCase.ResetPasswordUseCase
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryEvent
import care.intouch.app.feature.authorization.presentation.ui.models.PasswordRecoveryScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.RecoveryMove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordRecoveryScreenState())
    val uiState: StateFlow<PasswordRecoveryScreenState> = _uiState

    fun onEvent(event: PasswordRecoveryEvent) {
        when (event) {
            is PasswordRecoveryEvent.OnPasswordRecovery -> recoverPassword(event.email)
            is PasswordRecoveryEvent.ResetUiState -> resetUiState()
            is PasswordRecoveryEvent.OnTextFieldChange -> onTextFieldChange(event.text)
        }
    }

    private fun recoverPassword(email: String) {
        viewModelScope.launch {
            val result = resetPasswordUseCase(email)

            when {
                result.isSuccess -> {
                    _uiState.update { passwordRecoveryScreenState ->
                        passwordRecoveryScreenState.copy(recoveryMove = RecoveryMove.SUCCESS)
                    }
                }

                result.isFailure -> {
                    val response = result.exceptionOrNull()

                    if (response?.message == USER_NOT_EXIST) {
                        _uiState.update { passwordRecoveryScreenState ->
                            passwordRecoveryScreenState.copy(
                                recoveryMove = RecoveryMove.USER_NOT_EXIST
                            )
                        }
                    } else {
                        _uiState.update { passwordRecoveryScreenState ->
                            passwordRecoveryScreenState.copy(
                                recoveryErrorMessage = "${response?.message}",
                                recoveryMove = RecoveryMove.FAILURE
                            )
                        }
                    }

                }
            }
        }
    }

    private fun resetUiState() {

        _uiState.update { passwordRecoveryScreenState ->
            passwordRecoveryScreenState.copy(
                recoveryErrorMessage = BLANC_STRING,
                recoveryMove = RecoveryMove.UNDEFINED,
                enableButton = passwordRecoveryScreenState.textFieldValue.isNotEmpty()
            )
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