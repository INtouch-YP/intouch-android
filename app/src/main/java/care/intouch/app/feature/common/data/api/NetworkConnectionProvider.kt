package care.intouch.app.feature.common.data.api

interface NetworkConnectionProvider {
    fun isConnected(): Boolean
}