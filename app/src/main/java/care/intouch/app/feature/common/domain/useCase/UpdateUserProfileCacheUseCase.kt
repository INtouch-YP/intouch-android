package care.intouch.app.feature.common.domain.useCase

import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface UpdateUserProfileCacheUseCase {

    suspend operator fun invoke(): Resource<Boolean, ErrorEntity>

    class Base @Inject constructor(
        private val userRepository: UserRepository,
        private val userStorage: UserStorage
    ) : UpdateUserProfileCacheUseCase {
        override suspend fun invoke(): Resource<Boolean, ErrorEntity> {
            return when (val userInfo = userRepository.getUser()) {
                is Resource.Success -> {
                    Resource.Success(userStorage.save(userInfo.data))
                }

                is Resource.Error -> {
                    Resource.Error(userInfo.error)
                }
            }
        }
    }
}