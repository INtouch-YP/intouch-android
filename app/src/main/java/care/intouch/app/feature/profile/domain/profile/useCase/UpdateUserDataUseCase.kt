package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.UpdateUserDataResponse
import javax.inject.Inject

interface UpdateUserDataUseCase {
    suspend operator fun invoke(userData: ProfileData, id: Int) : Result<UpdateUserDataResponse>

    class Base @Inject constructor(
        private val updateUserDataRepository: UpdateUserDataRepository
    ): UpdateUserDataUseCase {
        override suspend fun invoke(userData: ProfileData, id: Int): Result<UpdateUserDataResponse> {
            return updateUserDataRepository.redactUserData(userData, id)
        }
    }
}