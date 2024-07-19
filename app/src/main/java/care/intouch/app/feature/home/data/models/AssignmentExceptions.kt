package care.intouch.app.feature.home.data.models

import java.io.IOException

sealed class AssignmentExceptions(
    override val message: String,
) : IOException(message) {
    data class NoInternetConnection(
        override val message: String = NO_INTERNET_CONNECTION
    ) : AssignmentExceptions(message)

    data class Undefined(override val message: String = UNDEFINED_MESSAGE) :
        AssignmentExceptions(message)

    companion object {
        const val NO_INTERNET_CONNECTION = "No internet connection"
        const val UNDEFINED_MESSAGE = "Undefined"
    }
}