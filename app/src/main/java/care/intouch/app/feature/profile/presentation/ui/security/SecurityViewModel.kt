package care.intouch.app.feature.profile.presentation.ui.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

            is SecurityEvent.OnVerifyCurrentPassword -> {
                verifyCurrentPassword(event.password)
            }
        }
    }

    private fun savePassword() {

    }

    private fun verifyCurrentPassword(password: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            _state.update { securityState ->
                securityState.copy(
                    errorCurrentPassword = PasswordInvalidType.CORRECT,
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
}