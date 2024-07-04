package care.intouch.app.feature.authorization.presentation.ui.models

sealed class PasswordRecoveryEvent {
    data object OnPasswordRecovery : PasswordRecoveryEvent()
    class OnTextFieldChange(val text: String) : PasswordRecoveryEvent()
}