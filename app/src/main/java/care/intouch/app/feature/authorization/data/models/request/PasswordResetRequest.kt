package care.intouch.app.feature.authorization.data.models.request

import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetRequest(
    val email: String
)
