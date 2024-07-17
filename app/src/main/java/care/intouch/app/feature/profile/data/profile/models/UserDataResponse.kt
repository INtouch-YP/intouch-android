package care.intouch.app.feature.profile.data.profile.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val email: String,
    @SerialName("date_of_birth")
    val dateOfBirth: String?,
    val photo: String
)