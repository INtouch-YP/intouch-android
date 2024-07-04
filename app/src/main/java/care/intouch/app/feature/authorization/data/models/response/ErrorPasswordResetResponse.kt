package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorPasswordResetResponse(
    val email: List<String>
)
