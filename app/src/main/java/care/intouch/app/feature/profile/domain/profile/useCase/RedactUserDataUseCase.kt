package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.data.profile.models.RedactUserDataResponse
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import kotlinx.coroutines.flow.Flow
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