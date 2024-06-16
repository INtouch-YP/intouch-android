package care.intouch.app.feature.profile.presentation.ui.security

sealed class SecurityEvent {
    data object OnCallFormForDelete: SecurityEvent()
    data object OnDeleteProfile: SecurityEvent()
    data object OnCancelDeleteProfile: SecurityEvent()
    data class OnVerifyCurrentPassword(val password: String): SecurityEvent()
    data class OnSavePassword(
        val password: String,
        val confirmPassword: String
    ): SecurityEvent()
}