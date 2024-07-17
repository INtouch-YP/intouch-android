package care.intouch.app.feature.profile.data.profile.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserEmailRequest(
    @SerialName("new_email") val newEmail: String
)
