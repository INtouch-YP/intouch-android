package care.intouch.app.feature.authorization.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccountModel(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
)