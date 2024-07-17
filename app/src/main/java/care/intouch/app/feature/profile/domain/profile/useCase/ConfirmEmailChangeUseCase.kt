package care.intouch.app.feature.profile.domain.profile.useCase

import javax.inject.Inject

interface ConfirmEmailChangeUseCase {
    suspend operator fun invoke(id: String, token: String)

    class Base @Inject constructor(
        private val confirmEmailChangeRepository: ConfirmEmailChangeRepository
    ): ConfirmEmailChangeUseCase {
        override suspend fun invoke(id: String, token: String) {
            confirmEmailChangeRepository.confirmEmailChange(id, token)
        }
    }
}