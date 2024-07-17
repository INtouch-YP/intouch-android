package care.intouch.app.feature.profile.domain.profile.useCase

import care.intouch.app.feature.common.domain.errors.AuthenticationError

interface ConfirmEmailChangeRepository {
    suspend fun confirmEmailChange(id: String, token: String)
}