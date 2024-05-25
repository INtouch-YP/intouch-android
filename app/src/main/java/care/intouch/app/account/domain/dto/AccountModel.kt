package care.intouch.app.account.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccountModel(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
)
