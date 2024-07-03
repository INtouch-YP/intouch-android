package care.intouch.app.feature.authorization.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenRequest(
    val username: String,
    val password: String,
)