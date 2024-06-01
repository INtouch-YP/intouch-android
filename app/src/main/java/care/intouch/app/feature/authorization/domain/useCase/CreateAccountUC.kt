package care.intouch.app.feature.authorization.domain.useCase

interface CreateAccountUC {
    suspend operator fun invoke(userId: Int, accessToken: String, refreshToken: String)
}