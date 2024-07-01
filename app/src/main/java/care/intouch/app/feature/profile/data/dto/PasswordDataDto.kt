package care.intouch.app.feature.profile.data.dto

import kotlinx.serialization.SerialName

data class PasswordDataDto(
    @SerialName("password")
    val currentPassword: String,
    @SerialName("new_password")
    val newPassword: String,
    @SerialName("confirm_new_password")
    val newConfirmationPassword: String,
)
