package care.intouch.app.feature.profile.data.profile.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserEmailResponseDto(
    @SerialName("non_field_errors")
    val message: String = "",
    @SerialName("message")
    val message2: String = ""
)
