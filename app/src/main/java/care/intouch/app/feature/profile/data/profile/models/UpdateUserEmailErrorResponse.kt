package care.intouch.app.feature.profile.data.profile.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserEmailErrorResponse(
    @SerialName("non_field_errors")
    val detail: List<String>? = null,
)
