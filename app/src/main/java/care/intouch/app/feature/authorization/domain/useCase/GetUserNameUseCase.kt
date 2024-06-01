package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface GetUserNameUseCase {
    suspend operator fun invoke(): Resource<String, ErrorEntity>
    class Base @Inject constructor(
        private val repository: UserRepository
    ) : GetUserNameUseCase {
        override suspend fun invoke(): Resource<String, ErrorEntity> {
            return when (val result = repository.getUser()) {
                is Resource.Success -> {
                    Resource.Success(result.data.firstName)
                }

                is Resource.Error -> {
                    Resource.Error(result.error)
                }
            }
        }
    }
}