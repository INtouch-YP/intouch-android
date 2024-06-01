package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.exception.UserException
import care.intouch.app.feature.authorization.data.models.response.ErrorResponse
import care.intouch.app.feature.common.data.models.exception.NetworkException
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkToUserExceptionMapper @Inject constructor(private val json: Json) {
    fun handleException(exception: NetworkException): UserException {
        return when (exception) {
            is NetworkException.Unauthorized -> {
                val response = handleErrorResponse<ErrorResponse>(exception.errorBody)
                UserException.User.InvalidToken(
                    message = response.detail ?: BLANC_ERROR_MESSAGE,
                )
            }

            else -> {
                handleCommonException(exception)
            }
        }
    }

    private fun handleCommonException(exception: NetworkException): UserException {
        return when (exception) {
            is NetworkException.NoInternetConnection -> {
                UserException.NoInternetConnection(message = exception.errorBody)
            }

            else -> {
                UserException.Undefined(message = exception.errorBody)
            }
        }
    }

    private inline fun <reified T> handleErrorResponse(errorMessage: String): T {
        try {
            return json.decodeFromString<T>(errorMessage)

        } catch (e: Exception) {
            throw AuthenticationException.Undefined(COULD_NOT_CONVERT_TO_ERROR_RESPONSE)
        }
    }

    companion object {
        const val COULD_NOT_CONVERT_TO_ERROR_RESPONSE = "Couldn't convert to ErrorResponse"
        const val BLANC_ERROR_MESSAGE = "No error message"
    }
}