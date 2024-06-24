package care.intouch.app.feature.authorization.presentation.ui.models

sealed class PasswordRecoveryEvent {
    class OnPasswordRecovery(val email: String) : PasswordRecoveryEvent()
    data object ResetUiState : PasswordRecoveryEvent()
    class OnTextFieldChange(val text: String) : PasswordRecoveryEvent()
}