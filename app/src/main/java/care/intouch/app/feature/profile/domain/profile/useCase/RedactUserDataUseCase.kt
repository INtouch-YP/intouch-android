package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.authorization.domain.models.User
import javax.inject.Inject

interface RedactUserDataUseCase {
    suspend operator fun invoke(userData: User) : Boolean

    class Base @Inject constructor(
        private val redactUserDataPatch: RedactUserDataPatch
    ): RedactUserDataUseCase {
        override suspend fun invoke(userData: User): Boolean {
            return redactUserDataPatch.redactUserData(userData)
        }

    }

}