package care.intouch.app.feature.profile.domain.impl

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.profile.data.api.DeleteProfileRepository
import care.intouch.app.feature.profile.domain.useCase.DeleteProfileUseCase
import javax.inject.Inject

class DeleteProfileUseCaseImpl @Inject constructor(
    private val repository: DeleteProfileRepository
): DeleteProfileUseCase {
    override suspend fun invoke(): Resource<Unit, ErrorEntity> {
        return repository.deleteProfile()
    }
}