package care.intouch.app.feature.profile.presentation.ui.security

sealed interface SecuritySideEffect {
    data object NavigateToDeleteProfile : SecuritySideEffect
    data object NavigateBack : SecuritySideEffect
}