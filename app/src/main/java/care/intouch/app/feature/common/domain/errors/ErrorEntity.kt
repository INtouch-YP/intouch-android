package care.intouch.app.feature.common.domain.errors

sealed class ErrorEntity(open val message: String) {
    data class UnknownError(override val message: String = NO_ERROR_MESSAGE) : ErrorEntity(message)
    companion object {
        const val NO_ERROR_MESSAGE = "No error message"
    }
}

