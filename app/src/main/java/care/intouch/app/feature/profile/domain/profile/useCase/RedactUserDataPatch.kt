package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.authorization.domain.models.User

interface RedactUserDataPatch {
    suspend fun redactUserData(userData: User):Boolean
}