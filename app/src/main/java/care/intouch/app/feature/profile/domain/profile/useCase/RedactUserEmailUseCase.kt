package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.RedactUserEmailResponse
import javax.inject.Inject

interface RedactUserEmailUseCase {
    suspend operator fun invoke(newEmail: String): RedactUserEmailResponse

    class Base @Inject constructor(
        private val redactUserEmailRepository: RedactUserEmailRepository
    ): RedactUserEmailUseCase {
        override suspend fun invoke(newEmail: String): RedactUserEmailResponse {
            return redactUserEmailRepository.redactUserEmail(newEmail)
        }
    }
}