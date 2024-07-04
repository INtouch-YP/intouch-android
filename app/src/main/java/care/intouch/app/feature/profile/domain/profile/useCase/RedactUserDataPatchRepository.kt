package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.RedactUserDataResponse

interface RedactUserDataPatchRepository {
    suspend fun redactUserData(userData: ProfileData, id: Int): RedactUserDataResponse
}