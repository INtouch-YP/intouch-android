package care.intouch.app.feature.profile.data.profile.models

import kotlinx.serialization.SerialName

data class UpdateUserDataRequest(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String
)
