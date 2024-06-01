package care.intouch.app.feature.authorization.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TokensRequest(
    val refresh: String
)