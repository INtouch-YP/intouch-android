package care.intouch.app.feature.profile.presentation.ui.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            _state.update { securityState ->
                //todo request to backend
                securityState.copy(
                    errorCurrentPassword = PasswordValidType.INCORRECT_CURRENT_PASSWORD,
                )
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
            _state.update { securityState ->
                //todo request to backend
                securityState.copy(
                    uiState = SecurityUiState.ProfileDeleted
                )
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
        currentPassword: String,
    ): Boolean {
        return passwordValid == PasswordValidType.CORRECT
                && confirmPasswordValid == PasswordValidType.CORRECT
                && currentPassword.isNotBlank()
    }

}