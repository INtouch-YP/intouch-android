package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.UpdateUserEmailResponse

interface UpdateUserEmailRepository {
    suspend fun redactUserEmail(newEmail: String): Result<UpdateUserEmailResponse>
}