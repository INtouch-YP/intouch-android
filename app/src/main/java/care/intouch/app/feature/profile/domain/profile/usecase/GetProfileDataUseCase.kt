package care.intouch.app.feature.profile.domain.profile.usecase

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.repository.GetProfileDataRepository
import javax.inject.Inject

interface GetProfileDataUseCase {
    suspend operator fun invoke(): ProfileData?

    class Base @Inject constructor(
        private val repository: GetProfileDataRepository
    ) : GetProfileDataUseCase {
        override suspend fun invoke(): ProfileData? {
            return repository.getProfileDataFromSharedPreferences()
        }

    }
}