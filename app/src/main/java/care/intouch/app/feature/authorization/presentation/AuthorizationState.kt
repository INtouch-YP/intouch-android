package care.intouch.app.feature.authorization.presentation

data class AuthorizationState(
    val uiState: AuthorizationUiState = AuthorizationUiState.Loading,
    val userName: String = "",
    val error: InputPasswordError? = null,
    val agreementToTerm: Boolean = true
)
