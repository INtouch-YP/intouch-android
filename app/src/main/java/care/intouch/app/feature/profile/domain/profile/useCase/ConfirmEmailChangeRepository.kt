package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.profile.domain.profile.models.EmailChangeResponse

interface ConfirmEmailChangeRepository {
    suspend fun confirmEmailChange(id: String, token: String): Result<EmailChangeResponse>
}