package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.RedactUserDataResponse
import javax.inject.Inject

interface RedactUserDataUseCase {
    suspend operator fun invoke(userData: ProfileData, id: Int) : RedactUserDataResponse

    class Base @Inject constructor(
        private val redactUserDataPatchRepository: RedactUserDataPatchRepository
    ): RedactUserDataUseCase {
        override suspend fun invoke(userData: ProfileData, id: Int): RedactUserDataResponse {
            return redactUserDataPatchRepository.redactUserData(userData, id)
        }
    }
}