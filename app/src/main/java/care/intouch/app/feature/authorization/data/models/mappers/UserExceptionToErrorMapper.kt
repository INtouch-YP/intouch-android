package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.exception.UserException
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.common.domain.errors.NetworkError
import care.intouch.app.feature.common.domain.errors.UserError
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import javax.inject.Inject

class UserExceptionToErrorMapper @Inject constructor() : BaseExceptionToErrorMapper() {
    override fun handleSpecificException(exception: Exception): ErrorEntity {
        return when (exception) {
            is UserException -> handleUserException(exception)
            else -> handleUnknownException(exception)
        }
    }

    private fun handleUserException(exception: UserException): ErrorEntity {
        return when (exception) {
            is UserException.NoInternetConnection -> NetworkError.NoInternetConnection(
                exception.message
            )

            is UserException.Undefined -> UserError.Unknown(exception.message)
            is UserException.User.MissingRequiredFields -> UserError.Unknown(exception.message)
            is UserException.User.InvalidToken -> UserError.Unauthorised(exception.message)
        }
    }

    private fun handleUnknownException(exception: Exception): ErrorEntity {
        return UserError.Unknown(exception.message ?: UNKNOWN_ERROR)
    }

    companion object {
        private const val UNKNOWN_ERROR = "Something went wrong"
    }

}