package care.intouch.app.feature.authorization.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val acceptPolicy: Boolean,
    val newEmailChanging: Boolean,
    val newEmailTemp: String
)