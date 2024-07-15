package care.intouch.app.feature.authorization.data.models

import care.intouch.app.feature.authorization.domain.dto.NewPasswordModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewPasswordRequest(
    @SerialName("new_password")
    val newPassword: String,
    @SerialName("confirm_new_password")
    val confirmNewPassword: String
)

fun NewPasswordModel.mapToRequest() : NewPasswordRequest {
    return NewPasswordRequest(
        newPassword = this.newPassword,
        confirmNewPassword = this.confirmNewPassword
    )
}

