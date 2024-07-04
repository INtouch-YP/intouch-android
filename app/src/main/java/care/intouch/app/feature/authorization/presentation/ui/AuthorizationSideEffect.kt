package care.intouch.app.feature.authorization.presentation.ui

sealed interface AuthorizationSideEffect {
    data object NavigateToCreatePinCode : AuthorizationSideEffect
}