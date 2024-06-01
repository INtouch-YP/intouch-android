package care.intouch.app.feature.common.domain.errors

sealed class NetworkError(override val message: String) : ErrorEntity(message) {
    data class NoInternetConnection(override val message: String) : NetworkError(message)
    data class NoConnectionToServer(override val message: String) : NetworkError(message)
}