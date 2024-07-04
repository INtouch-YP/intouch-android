package care.intouch.app.feature.profile.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordDataDto(
    @SerialName("password")
    val currentPassword: String,
    @SerialName("new_password")
    val newPassword: String,
    @SerialName("confirm_new_password")
    val newConfirmationPassword: String,
)
