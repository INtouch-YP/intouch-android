package care.intouch.app.feature.profile.domain.useCase

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.profile.domain.models.PasswordData

interface UpdatePasswordUseCase {
    suspend operator fun invoke(
        passwordData: PasswordData
    ): Resource<Unit, ErrorEntity>
}