package care.intouch.app.feature.profile.presentation.ui.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.profile.domain.models.PasswordData
import care.intouch.app.feature.profile.domain.useCase.DeleteProfileUseCase
import care.intouch.app.feature.profile.domain.useCase.UpdatePasswordUseCase
import care.intouch.app.feature.profile.presentation.ui.security.models.PasswordValidType
import care.intouch.app.feature.profile.presentation.ui.security.models.SecuritySideEffect
import care.intouch.app.feature.profile.presentation.ui.security.models.SecurityState
import care.intouch.app.feature.profile.presentation.ui.security.models.SecurityUiState
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
class SecurityViewModel @Inject constructor(
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase
): ViewModel() {
    private var _state = MutableStateFlow(SecurityState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SecuritySideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onEvent(event: SecurityEvent) {
        when(event) {
            is SecurityEvent.OnSavePassword -> {
                savePassword()
            }

            SecurityEvent.OnCallFormForDelete -> {
                callFormForDelete()
            }

            SecurityEvent.OnCancelDeleteProfile -> {
                cancelDeleteProfile()
            }

            SecurityEvent.OnDeleteProfile -> {
                deleteProfile()
            }

            is SecurityEvent.OnSetCurrentPassword -> {
                setCurrentPassword(event.password)
            }

            is SecurityEvent.OnSetPassword -> {
                checkPassword(event.password)
            }

            is SecurityEvent.OnSetConfirmPassword -> {
                checkConfirmPassword(event.confirmPassword)
            }

            SecurityEvent.OnBackButtonClick -> {
                viewModelScope.launch {
                    _sideEffect.emit(SecuritySideEffect.NavigateBack)
                }
            }

            SecurityEvent.OnDeleteProfileButtonClick -> {
                viewModelScope.launch {
                    _sideEffect.emit(SecuritySideEffect.NavigateToDeleteProfile)
                }
            }
        }
    }

    private fun savePassword() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val updatePassword = updatePasswordUseCase.invoke(
                PasswordData(
                    currentPassword = state.value.currentPassword,
                    newPassword = state.value.password,
                    newConfirmationPassword = state.value.confirmPassword
                )
            )
            when (updatePassword) {
                is Resource.Success -> {
                    _state.update { securityState ->
                        securityState.copy(
                            currentPasswordValidType = PasswordValidType.CORRECT,
                            password = "",
                            confirmPassword = "",
                            currentPassword = "",
                            passwordValidType = PasswordValidType.CORRECT,
                            confirmPasswordValidType = PasswordValidType.CORRECT,
                            isSuccessUpdate = true
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update { securityState ->
                        securityState.copy(
                            currentPasswordValidType = PasswordValidType.INCORRECT_CURRENT_PASSWORD,
                            isSuccessUpdate = false
                        )
                    }
                }
            }
        }
    }

    private fun setCurrentPassword(password: String) {
        viewModelScope.launch {
            _state.update { securityState ->
                securityState.copy(
                    currentPassword = password,
                    isEnable = isEnabled(
                        passwordValid = state.value.passwordValidType,
                        confirmPasswordValid = state.value.confirmPasswordValidType,
                        currentPassword = password
                    )
                )
            }
        }
    }

    private fun deleteProfile() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val deleteProfile = deleteProfileUseCase.invoke()
            when (deleteProfile) {
                is Resource.Success -> {
                    _state.update { securityState ->
                        securityState.copy(
                            uiState = SecurityUiState.ProfileDeleted
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update { securityState ->
                        securityState.copy(
                            uiState = SecurityUiState.ProfileDeleted
                        )
                    }
                }
            }
        }
    }

    private fun callFormForDelete() {
        viewModelScope.launch {
            _state.update { securityState ->
                securityState.copy(
                    uiState = SecurityUiState.DeleteProfile
                )
            }
        }
    }

    private fun cancelDeleteProfile() {
        viewModelScope.launch {
            _state.update { securityState ->
                securityState.copy(
                    uiState = SecurityUiState.SetPassword
                )
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
            _state.update { securityState ->
                securityState.copy(
                    password = password,
                    passwordValidType = validType,
                    isEnable = isEnabled(
                        passwordValid = validType,
                        confirmPasswordValid = state.value.confirmPasswordValidType,
                        currentPassword = state.value.currentPassword
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
            _state.update { securityState ->
                securityState.copy(
                    confirmPassword = password,
                    confirmPasswordValidType = validType,
                    isEnable = isEnabled(
                        passwordValid = state.value.passwordValidType,
                        confirmPasswordValid = validType,
                        currentPassword = state.value.currentPassword
                    )
                )
            }
        }
    }

    private fun checkPasswordFormat(password: String): PasswordValidType {
        return when {
            !Pattern.compile(SPACE_PATTERN).matcher(password).matches() -> {
                PasswordValidType.EXIST_SPACE
            }

            !Pattern.compile(ESPECIAL_SYMBOL_PATTERN).matcher(password).matches() -> {
                PasswordValidType.INVALID_SYMBOL
            }

            !Pattern.compile(SMALL_PATTERN).matcher(password).matches() -> {
                PasswordValidType.SMALL_PASSWORD
            }

            !Pattern.compile(BIG_PATTERN).matcher(password).matches() -> {
                PasswordValidType.BIG_PASSWORD
            }

            !Pattern.compile(MISSING_PATTERN).matcher(password).matches() -> {
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
        currentPassword: String,
    ): Boolean {
        return passwordValid == PasswordValidType.CORRECT
                && confirmPasswordValid == PasswordValidType.CORRECT
                && currentPassword.isNotBlank()
    }

    companion object {
        const val SMALL_PATTERN = "^.{8,}$"
        const val BIG_PATTERN = "^.{8,128}$"
        const val ESPECIAL_SYMBOL_PATTERN = "^[a-zA-Z\\d~!?@#\$%^&*_+\\-{}()\\[\\]<>\\/\\\\|\"'.,:;]*\$"
        const val MISSING_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$"
        const val SPACE_PATTERN = "^\\S*\$"
    }
}