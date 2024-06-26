package care.intouch.app.feature.profile.presentation.ui.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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
                viewModelScope.launch(context = Dispatchers.IO) {
                    _state.update { securityState ->
                        securityState.copy(
                            currentPassword = event.password
                        )
                    }
                }
            }

            is SecurityEvent.OnSetPassword -> {
                checkPassword(event.password)
            }

            is SecurityEvent.OnSetConfirmPassword -> {
                checkConfirmPassword(event.confirmPassword)
            }
        }
    }

    private fun savePassword() {

    }

    private fun verifyCurrentPassword(password: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    errorCurrentPassword = PasswordValidType.CORRECT,
                )
            }
        }
    }

    private fun deleteProfile() {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    uiState = SecurityUiState.ProfileDeleted
                )
            }
        }
    }

    private fun callFormForDelete() {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    uiState = SecurityUiState.DeleteProfile
                )
            }
        }
    }

    private fun cancelDeleteProfile() {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    uiState = SecurityUiState.SetPassword
                )
            }
        }
    }

    private fun checkPassword(password: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    password = password,
                    passwordValidType = if (password.isNotBlank()) {
                        isValidPasswordFormat(password)
                    } else {
                        PasswordValidType.CORRECT
                    }
                )
            }
        }
    }

    private fun checkConfirmPassword(password: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    confirmPassword = password,
                    confirmPasswordValidType = when {
                        state.value.password.isBlank() || password.isBlank() -> PasswordValidType.CORRECT
                        state.value.password == password -> PasswordValidType.CORRECT
                        else -> PasswordValidType.NOT_MATCH
                    },
                    isEnabled = isEnabled(
                        passwordValid = state.value.passwordValidType,
                        confirmPasswordValid = state.value.confirmPasswordValidType,
                        currentPassword = state.value.currentPassword
                    )
                )
            }
        }
    }

    private fun isValidPasswordFormat(password: String): PasswordValidType {
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