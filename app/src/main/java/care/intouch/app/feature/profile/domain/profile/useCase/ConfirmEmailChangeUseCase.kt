package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.EmailChangeResponse
import javax.inject.Inject

interface ConfirmEmailChangeUseCase {
    suspend operator fun invoke(id: String, token: String): Result<EmailChangeResponse>

    class Base @Inject constructor(
        private val confirmEmailChangeRepository: ConfirmEmailChangeRepository
    ): ConfirmEmailChangeUseCase {
        override suspend fun invoke(id: String, token: String): Result<EmailChangeResponse> {
            return confirmEmailChangeRepository.confirmEmailChange(id, token)
        }
    }
}