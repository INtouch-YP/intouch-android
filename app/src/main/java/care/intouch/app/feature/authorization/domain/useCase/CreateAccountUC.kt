package care.intouch.app.feature.authorization.domain.useCase

interface CreateAccountUC {
    suspend operator fun invoke(accessToken: String, refreshToken: String)
}