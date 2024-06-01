package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.exception.UserException
import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.authorization.domain.models.User
import javax.inject.Inject

class UserToDomainMapper @Inject constructor() {
    fun toUser(response: UserResponse): User {
        with(response) {
            if (id == null || firstName == null || email == null) {
                throwException()
            }
            return User(
                id = id,
                firstName = firstName,
                email = email,
                lastName = lastName ?: DEFAULT_LAST_NAME,
                acceptPolicy = acceptPolicy ?: DEFAULT_ACCEPT_POLICY,
                newEmailTemp = newEmailTemp ?: DEFAULT_NEW_EMAIL_TEMP,
                newEmailChanging = newEmailChanging ?: DEFAULT_NEW_EMAIL_CHANGING
            )
        }
    }

    private fun throwException(): Nothing {
        throw UserException.User.MissingRequiredFields(
            "UserResponse is missing required fields: id, firstName, or email."
        )
    }

    companion object {
        private const val DEFAULT_LAST_NAME = ""
        private const val DEFAULT_ACCEPT_POLICY = false
        private const val DEFAULT_NEW_EMAIL_TEMP = ""
        private const val DEFAULT_NEW_EMAIL_CHANGING = false
    }
}