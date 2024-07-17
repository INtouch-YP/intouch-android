package care.intouch.app.feature.common.data

import care.intouch.app.feature.common.data.models.exception.NetworkException
import java.io.IOException
import javax.inject.Inject

class NetworkErrorCodeToExceptionMapper @Inject constructor() {

    fun getException(errorMessage: String, responseCode: Int): IOException {
        return when (responseCode) {
            400 -> NetworkException.BadRequest(errorMessage, responseCode)
            401 -> NetworkException.Unauthorized(errorMessage, responseCode)
            404 -> NetworkException.NotFound(errorMessage, responseCode)
            500 -> NetworkException.InternalServerError(errorMessage, responseCode)

            else -> NetworkException.Undefined(errorMessage, responseCode)
        }
    }
}