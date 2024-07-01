package care.intouch.app.feature.profile.domain.useCase

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface DeleteProfileUseCase {
    suspend operator fun invoke(): Resource<Unit, ErrorEntity>
}