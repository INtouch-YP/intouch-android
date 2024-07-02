package care.intouch.app.feature.authorization.presentation.ui.models

sealed class PasswordRecoveryEvent {
    class OnPasswordRecovery(val email: String) : PasswordRecoveryEvent()
    class OnTextFieldChange(val text: String) : PasswordRecoveryEvent()
}