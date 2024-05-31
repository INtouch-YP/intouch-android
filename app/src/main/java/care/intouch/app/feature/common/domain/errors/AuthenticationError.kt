package care.intouch.app.feature.common.domain.errors

sealed class AuthenticationError(message: String) : ErrorEntity(message) {
    data class Unknown(
        override val message: String = NO_ERROR_MESSAGE
    ) : AuthenticationError(message)

    data class InvalidToken(override val message: String) : AuthenticationError(message)
    data class UserNotExist(override val message: String) : AuthenticationError(message)
    data class AlreadyActivated(override val message: String) : AuthenticationError(message)
}