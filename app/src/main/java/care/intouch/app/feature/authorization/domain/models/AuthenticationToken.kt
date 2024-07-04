package care.intouch.app.feature.authorization.domain.models

data class AuthenticationToken(
    val accessToken: String,
    val refreshToken: String,
)