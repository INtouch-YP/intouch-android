package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailResponseDto

interface RedactUserEmailRepository {
    suspend fun redactUserEmail(newEmail: String): Result<UpdateUserEmailResponseDto>
}