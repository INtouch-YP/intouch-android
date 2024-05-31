package care.intouch.app.feature.authorization.domain.models

data class Authentication(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)