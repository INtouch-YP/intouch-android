package care.intouch.app.feature.authorization.data.models.exception

import java.io.IOException

sealed class UserException(
    override val message: String,
) : IOException(message) {
    data class NoInternetConnection(
        override val message: String = NO_INTERNET_CONNECTION
    ) : UserException(message)

    data class Undefined(override val message: String = UNDEFINED_MESSAGE) :
        UserException(message)

    sealed class User(message: String) : UserException(message) {
        class InvalidToken(
            override val message: String,
        ) : User(message)

        class MissingRequiredFields(
            override val message: String,
        ) : User(message)
    }

    companion object {
        const val NO_INTERNET_CONNECTION = "No internet connection"
        const val UNDEFINED_MESSAGE = "Undefined"
    }
}