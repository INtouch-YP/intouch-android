package care.intouch.app.feature.profile.presentation.ui.security.models

sealed class SecurityUiState {
    data object SetPassword: SecurityUiState()
    data object DeleteProfile: SecurityUiState()
    data object ProfileDeleted: SecurityUiState()
}
