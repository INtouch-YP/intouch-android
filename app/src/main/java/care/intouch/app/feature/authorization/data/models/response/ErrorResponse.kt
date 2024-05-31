package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val detail: String? = null,
)