package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.UpdateUserDataResponse

interface UpdateUserDataRepository {
    suspend fun redactUserData(userData: ProfileData, id: Int): Result<UpdateUserDataResponse>
}