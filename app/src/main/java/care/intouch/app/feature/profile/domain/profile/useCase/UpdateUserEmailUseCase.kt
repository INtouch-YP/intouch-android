package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.UpdateUserEmailResponse
import javax.inject.Inject

interface UpdateUserEmailUseCase {
    suspend operator fun invoke(newEmail: String): Result<UpdateUserEmailResponse>

    class Base @Inject constructor(
        private val updateUserEmailRepository: UpdateUserEmailRepository
    ): UpdateUserEmailUseCase {
        override suspend fun invoke(newEmail: String): Result<UpdateUserEmailResponse> {
            return updateUserEmailRepository.redactUserEmail(newEmail)
        }
    }
}