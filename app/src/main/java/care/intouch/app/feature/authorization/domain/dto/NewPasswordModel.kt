package care.intouch.app.feature.authorization.domain.dto

data class NewPasswordModel(
    val newPassword: String,
    val confirmNewPassword: String
)
