package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataPatch

class RedactUserDataPatchImpl(): RedactUserDataPatch {
    override suspend fun redactUserData(userData: User): Boolean {
        TODO("Not yet implemented")
    }
}