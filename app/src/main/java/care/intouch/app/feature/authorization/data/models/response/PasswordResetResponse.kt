package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetResponse(
    val message: String
)
