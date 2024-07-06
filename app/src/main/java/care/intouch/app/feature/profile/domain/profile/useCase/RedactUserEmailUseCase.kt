package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailResponseDto
import javax.inject.Inject

interface RedactUserEmailUseCase {
    suspend operator fun invoke(newEmail: String): Result<UpdateUserEmailResponseDto>

    class Base @Inject constructor(
        private val redactUserEmailRepository: RedactUserEmailRepository
    ): RedactUserEmailUseCase {
        override suspend fun invoke(newEmail: String): Result<UpdateUserEmailResponseDto> {
            return redactUserEmailRepository.redactUserEmail(newEmail)
        }
    }
}