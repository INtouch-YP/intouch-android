package care.intouch.app.account.domain.api

interface CreateAccountUC {
    suspend operator fun invoke(userId: Int, accessToken: String, refreshToken: String)
}