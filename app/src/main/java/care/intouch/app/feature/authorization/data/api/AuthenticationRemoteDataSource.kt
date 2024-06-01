package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse

interface AuthenticationRemoteDataSource {
    suspend fun confirmEmail(id: Int, token: String): ConfirmEmailResponse
}