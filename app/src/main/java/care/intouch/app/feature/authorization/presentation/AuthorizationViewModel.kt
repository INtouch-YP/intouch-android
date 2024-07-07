package care.intouch.app.feature.authorization.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.ConfirmEmailUseCase
import care.intouch.app.feature.authorization.domain.useCase.GetUserFullNameUseCase
import care.intouch.app.feature.authorization.domain.useCase.SetPasswordUseCase
import care.intouch.app.feature.authorization.presentation.ui.AuthorizationSideEffect
import care.intouch.app.feature.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCase,
    private val getUserFullNameUseCase: GetUserFullNameUseCase,
    private val setPasswordUseCase: SetPasswordUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(AuthorizationState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<AuthorizationSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onEvent(event: AuthorizationEvent) {
        when (event) {
            is AuthorizationEvent.OnGetUserInfo -> {
                getUserInfo()
                confirmEmail(event.userId, event.token)
            }

            is AuthorizationEvent.OnSavePassword -> {
                savePassword(event.password, event.confirmPassword)
            }

            is AuthorizationEvent.OnSetPassword -> {
                checkPassword(event.password)
            }

            is AuthorizationEvent.OnSetConfirmPassword -> {
                checkConfirmPassword(event.confirmPassword)
            }

            is AuthorizationEvent.OnUpdateAgreementToTerm -> {
                viewModelScope.launch {
                    _state.update { authorizationState ->
                        authorizationState.copy(
                            isEnableAgreementToTerm = event.isEnable,
                            isEnable = isEnabled(
                                passwordValid = state.value.passwordValidType,
                                confirmPasswordValid = state.value.confirmPasswordValidType,
                                agreementToTerm = event.isEnable
                            )
                        )
                    }
                }
            }

            AuthorizationEvent.OnCreatePinCodeButtonClick -> {
                viewModelScope.launch {
                    _sideEffect.emit(AuthorizationSideEffect.NavigateToCreatePinCode)
                }
            }
        }
    }

    private fun confirmEmail(userId: String?, email: String?) {
        if (userId != null && email != null) {
            viewModelScope.launch(context = Dispatchers.IO) {
                confirmEmailUseCase.invoke(userId.toInt(), email)
            }
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch(context = Dispatchers.IO) {
            when (val userInfo = getUserFullNameUseCase.invoke()) {
                is Resource.Success -> {
                    _state.update { authorizationState ->
                        authorizationState.copy(
                            uiState = AuthorizationUiState.SetPassword,
                            userName = userInfo.data
                        )
                    }
                }

                is Resource.Error -> {
                    Log.d("TEST", "error ${userInfo.error.message}")
                }
            }
        }
    }

    private fun checkPassword(password: String) {
        viewModelScope.launch {
            val validType = if (password.isNotBlank()) {
                checkPasswordFormat(password)
            } else {
                PasswordValidType.CORRECT
            }
            _state.update { authorizationState ->
                authorizationState.copy(
                    password = password,
                    passwordValidType = validType,
                    isEnable = password.isNotBlank() && isEnabled(
                        passwordValid = validType,
                        confirmPasswordValid = state.value.confirmPasswordValidType,
                        agreementToTerm = state.value.isEnableAgreementToTerm
                    )
                )
            }
        }
    }

    private fun checkConfirmPassword(password: String) {
        viewModelScope.launch {
            val validType = when {
                state.value.password.isBlank() || password.isBlank() -> PasswordValidType.CORRECT
                state.value.password == password -> PasswordValidType.CORRECT
                else -> PasswordValidType.NOT_MATCH
            }
            _state.update { authorizationState ->
                authorizationState.copy(
                    confirmPassword = password,
                    confirmPasswordValidType = validType,
                    isEnable = password.isNotBlank() && isEnabled(
                        passwordValid = state.value.passwordValidType,
                        confirmPasswordValid = validType,
                        agreementToTerm = state.value.isEnableAgreementToTerm
                    )
                )
            }
        }
    }

    private fun savePassword(password: String, confirmPassword: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            when (val result = setPasswordUseCase.invoke(password, confirmPassword)) {
                is Resource.Success -> {
                    _state.update { registrationState ->
                        registrationState.copy(
                            uiState = AuthorizationUiState.Authorized
                        )
                    }
                }

                is Resource.Error -> {
                    Log.d("TEST", "error ${result.error}")
                }
            }
        }
    }

    private fun checkPasswordFormat(password: String): PasswordValidType {
        val smallPattern = "^.{8,}$"
        val bigPattern = "^.{8,128}$"
        val especialSymbol = "^[a-zA-Z\\d~!?@#\$%^&*_+\\-{}()\\[\\]<>\\/\\\\|\"'.,:;]*\$"

        val missingPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$"
        val spacePattern = "^\\S*\$"
        return when {
            !Pattern.compile(spacePattern).matcher(password).matches() -> {
                PasswordValidType.EXIST_SPACE
            }

            !Pattern.compile(especialSymbol).matcher(password).matches() -> {
                PasswordValidType.INVALID_SYMBOL
            }

            !Pattern.compile(smallPattern).matcher(password).matches() -> {
                PasswordValidType.SMALL_PASSWORD
            }

            !Pattern.compile(bigPattern).matcher(password).matches() -> {
                PasswordValidType.BIG_PASSWORD
            }

            !Pattern.compile(missingPattern).matcher(password).matches() -> {
                PasswordValidType.MISSING_SYMBOL
            }

            else -> {
                PasswordValidType.CORRECT
            }
        }
    }

    private fun isEnabled(
        passwordValid: PasswordValidType,
        confirmPasswordValid: PasswordValidType,
        agreementToTerm: Boolean,
    ): Boolean {
        return passwordValid == PasswordValidType.CORRECT
                && confirmPasswordValid == PasswordValidType.CORRECT
                && agreementToTerm
    }
}