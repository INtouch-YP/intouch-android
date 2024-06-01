package care.intouch.app.feature.common.data.api

import care.intouch.app.feature.authorization.data.models.TokensRequest
import care.intouch.app.feature.authorization.data.models.response.TokensResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokensApiService {
    @POST("/api/v1/token/refresh/")
    suspend fun getTokensByRefreshToken(
        @Body tokensRequest: TokensRequest
    ): TokensResponse
}