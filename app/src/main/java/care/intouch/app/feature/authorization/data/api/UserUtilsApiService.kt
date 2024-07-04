package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.request.PasswordResetRequest
import care.intouch.app.feature.authorization.data.models.response.PasswordResetResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserUtilsApiService {
    @POST("api/v1/password/reset/")
    suspend fun resetPassword(@Body request: PasswordResetRequest): PasswordResetResponse
}