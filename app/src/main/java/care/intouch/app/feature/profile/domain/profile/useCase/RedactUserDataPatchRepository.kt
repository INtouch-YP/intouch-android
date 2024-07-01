package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.data.profile.models.RedactUserDataResponse
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import kotlinx.coroutines.flow.Flow

interface RedactUserDataPatchRepository {
    suspend fun redactUserData(userData: ProfileData, id: Int): RedactUserDataResponse
}