package care.intouch.app.feature.authorization.presentation.ui.models

sealed interface PasswordRecoverySideEffect {
    data object Success: PasswordRecoverySideEffect
    data object UserNotExist: PasswordRecoverySideEffect
    data object Failure: PasswordRecoverySideEffect
}