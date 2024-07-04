package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import care.intouch.app.feature.authorization.data.models.response.TokensResponse

interface AuthenticationRemoteDataSource {
    suspend fun confirmEmail(id: Int, token: String): ConfirmEmailResponse
    suspend fun getToken(username: String, password: String): TokensResponse
}