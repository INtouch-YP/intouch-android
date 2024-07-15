package care.intouch.app.feature.profile.domain.impl

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.profile.data.api.UpdatePasswordRepository
import care.intouch.app.feature.profile.domain.models.PasswordData
import care.intouch.app.feature.profile.domain.useCase.UpdatePasswordUseCase
import javax.inject.Inject

class UpdatePasswordUseCaseImpl @Inject constructor(
    private val repository: UpdatePasswordRepository
): UpdatePasswordUseCase {
    override suspend fun invoke(
        passwordData: PasswordData
    ): Resource<Unit, ErrorEntity> {
        return repository.updatePassword(
            passwordData
        )
    }
}