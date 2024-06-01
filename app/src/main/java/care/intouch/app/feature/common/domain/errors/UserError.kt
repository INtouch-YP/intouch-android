package care.intouch.app.feature.common.domain.errors

sealed class UserError(message: String) : ErrorEntity(message) {
    data class Unknown(
        override val message: String = NO_ERROR_MESSAGE
    ) : UserError(message)

    data class Unauthorised(override val message: String) : UserError(message)
}