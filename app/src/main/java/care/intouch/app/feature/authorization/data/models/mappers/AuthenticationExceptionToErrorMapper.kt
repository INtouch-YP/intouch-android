package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.common.domain.errors.AuthenticationError
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.common.domain.errors.NetworkError
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import javax.inject.Inject

class AuthenticationExceptionToErrorMapper @Inject constructor() : BaseExceptionToErrorMapper() {
    override fun handleSpecificException(exception: Exception): ErrorEntity {
        return when (exception) {
            is AuthenticationException.Authentication.AlreadyActivated -> {
                AuthenticationError.AlreadyActivated(exception.message)
            }

            is AuthenticationException.Authentication.InvalidToken -> {
                AuthenticationError.InvalidToken(exception.message)
            }

            is AuthenticationException.Authentication.NotFound -> {
                AuthenticationError.UserNotExist(exception.message)
            }

            is AuthenticationException.NoInternetConnection -> {
                NetworkError.NoInternetConnection(exception.message)
            }

            else -> {
                ErrorEntity.UnknownError(exception.message ?: BLANC_STRING)
            }
        }

    }

    companion object {
        private const val BLANC_STRING = ""
    }

}
