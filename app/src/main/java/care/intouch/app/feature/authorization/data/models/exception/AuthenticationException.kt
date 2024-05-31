package care.intouch.app.feature.authorization.data.models.exception

import java.io.IOException

sealed class AuthenticationException(
    override val message: String,
) : IOException(message) {
    data class NoInternetConnection(
        override val message: String = NO_INTERNET_CONNECTION
    ) : AuthenticationException(message)

    data class Undefined(override val message: String = UNDEFINED_MESSAGE) :
        AuthenticationException(message)

    sealed class Authentication(message: String) : AuthenticationException(message) {
        class InvalidToken(
            override val message: String,
        ) : Authentication(message)

        class NotFound(
            override val message: String,
        ) : Authentication(message)

        class AlreadyActivated(
            override val message: String,
        ) : Authentication(message = message)

    }

    companion object {
        const val NO_INTERNET_CONNECTION = "No internet connection"
        const val UNDEFINED_MESSAGE = "Undefined"
    }
}