package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface GetUserFullNameUseCase {
    suspend operator fun invoke(): Resource<String, ErrorEntity>

    class Base @Inject constructor(
        private val userStorage: UserStorage,
    ) : GetUserFullNameUseCase {
        override suspend fun invoke(): Resource<String, ErrorEntity> {
            val user = userStorage.read()
            return if (user != null) {
                Resource.Success(
                    data = mapToUserName(user.firstName, user.lastName)
                )
            } else {
                Resource.Error(ErrorEntity.UnknownError(message = NO_USER_INFO))
            }
        }

        private fun mapToUserName(firstName: String, lastName: String): String {
            val fullName = buildString {
                append(firstName)
                if (lastName.isNotBlank()) {
                    append(" ")
                    append(lastName)
                }
            }
            return if (fullName.length > USER_FULL_NAME_MAX_LENGTH) {
                buildString {
                    append(firstName)
                    if (lastName.isNotBlank()) {
                        append(" ")
                        append(lastName[LAST_NAME_FIRST_LETTER_INDEX])
                        append(".")
                    }
                }
            } else fullName
        }
    }

    private companion object {
        private const val NO_USER_INFO = "No user info"
        private const val LAST_NAME_FIRST_LETTER_INDEX = 0
        private const val USER_FULL_NAME_MAX_LENGTH = 23
    }
}