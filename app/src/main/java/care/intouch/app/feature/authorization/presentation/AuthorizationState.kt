package care.intouch.app.feature.authorization.presentation

data class AuthorizationState(
    val uiState: AuthorizationUiState = AuthorizationUiState.SetPassword,
    val userName: String = "",
    val password: String = "",
    val passwordValidType: PasswordValidType = PasswordValidType.CORRECT,
    val confirmPassword: String = "",
    val confirmPasswordValidType: PasswordValidType = PasswordValidType.CORRECT,
    val isEnable: Boolean = false,
    val error: InputPasswordError? = null,
    val isEnableAgreementToTerm: Boolean = false
)
