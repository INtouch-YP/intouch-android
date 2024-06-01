package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    val email: String? = null,
    @SerialName("accept_policy") val acceptPolicy: Boolean? = null,
    @SerialName("new_email_changing") val newEmailChanging: Boolean? = null,
    @SerialName("new_email_temp") val newEmailTemp: String? = null
)