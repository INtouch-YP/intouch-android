package care.intouch.app.feature.profile.presentation.ui.security

sealed class SecurityEvent {
    data object OnCallFormForDelete: SecurityEvent()
    data object OnDeleteProfile: SecurityEvent()
    data object OnCancelDeleteProfile: SecurityEvent()
    data class OnSavePassword(
        val currentPassword: String,
        val password: String,
        val confirmPassword: String
    ): SecurityEvent()

    data class OnSetCurrentPassword(val password: String): SecurityEvent()
    data class OnSetPassword(val password: String): SecurityEvent()
    data class OnSetConfirmPassword(val confirmPassword: String): SecurityEvent()

    data object OnDeleteProfileButtonClick : SecurityEvent()
    data object OnBackButtonClick : SecurityEvent()
}