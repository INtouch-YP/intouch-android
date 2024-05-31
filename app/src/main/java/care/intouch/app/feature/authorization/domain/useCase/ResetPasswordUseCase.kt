package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.data.models.response.PasswordResetResponse
import care.intouch.app.feature.authorization.domain.api.UserRepository
import javax.inject.Inject

interface ResetPasswordUseCase {
    suspend operator fun invoke(email: String): Result<PasswordResetResponse>
    class Base @Inject constructor(
        private val repository: UserRepository
    ) : ResetPasswordUseCase {
        override suspend fun invoke(email: String): Result<PasswordResetResponse> {
            return repository.resetPassword(email)
        }
    }
}