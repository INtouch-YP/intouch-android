package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface GetUserNameUseCase {
    suspend operator fun invoke(): Resource<String, ErrorEntity>
    class Base @Inject constructor(
        private val userStorage: UserStorage,
    ) : GetUserNameUseCase {
        override suspend fun invoke(): Resource<String, ErrorEntity> {
            val user = userStorage.read()
            return if (user != null) {
                Resource.Success(
                    data = buildString {
                        append(user.firstName)
                        append(" ")
                        append(user.lastName)
                    })
            } else {
                Resource.Error(ErrorEntity.UnknownError(message = NO_USER_INFO))
            }
        }
    }

    private companion object {
        private const val NO_USER_INFO = "No user info"
    }
}