package care.intouch.app.feature.profile.presentation.ui.security

data class SecurityState(
    val uiState: SecurityUiState = SecurityUiState.SetPassword,
    val errorCurrentPassword: PasswordValidType = PasswordValidType.CORRECT,
    val isSuccessUpdate: Boolean? = null,
    val isEnabled: Boolean = false,
    val currentPassword: String = "",
    val currentPasswordValidType: PasswordValidType = PasswordValidType.CORRECT,
    val password: String = "",
    val passwordValidType: PasswordValidType = PasswordValidType.CORRECT,
    val confirmPassword: String = "",
    val confirmPasswordValidType: PasswordValidType = PasswordValidType.CORRECT,
)