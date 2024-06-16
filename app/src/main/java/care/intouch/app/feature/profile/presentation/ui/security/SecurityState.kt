package care.intouch.app.feature.profile.presentation.ui.security

data class SecurityState(
    val uiState: SecurityUiState = SecurityUiState.SetPassword,
    val errorCurrentPassword: PasswordInvalidType = PasswordInvalidType.CORRECT,
    val isSuccessUpdate: Boolean? = null,
    val isEnabled: Boolean = false
)