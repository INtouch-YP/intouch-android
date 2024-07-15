package care.intouch.app.feature.profile.domain.models

data class PasswordData(
    val currentPassword: String,
    val newPassword: String,
    val newConfirmationPassword: String,
)
