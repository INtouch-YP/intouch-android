package care.intouch.app.feature.profile.domain.usecase

import care.intouch.app.feature.profile.domain.models.ProfileData
import care.intouch.app.feature.profile.domain.repository.GetProfileDataRepository
import javax.inject.Inject

interface GetProfileDataUseCase {
    suspend operator fun invoke(): ProfileData

    class Base @Inject constructor(
        private val repository: GetProfileDataRepository
    ) : GetProfileDataUseCase {
        override suspend fun invoke(): ProfileData {
            return repository.getProfileDataFromSharedPreferences()
        }

    }
}