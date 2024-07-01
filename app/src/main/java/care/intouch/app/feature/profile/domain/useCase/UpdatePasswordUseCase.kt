package care.intouch.app.feature.profile.domain.useCase

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface UpdatePasswordUseCase {
    suspend operator fun invoke(
        currentPassword: String,
        newPassword: String,
        newConfirmationPassword: String
    ): Resource<Unit, ErrorEntity>
}