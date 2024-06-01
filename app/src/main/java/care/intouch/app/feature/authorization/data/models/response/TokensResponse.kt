package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    val access: String,
    val refresh: String
)