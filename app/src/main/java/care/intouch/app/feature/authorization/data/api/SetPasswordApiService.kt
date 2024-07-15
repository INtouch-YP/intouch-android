package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.NewPasswordRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SetPasswordApiService {
    @POST("/api/v1/password/reset/complete/")
    suspend fun setPassword(
        @Body passwords: NewPasswordRequest
    )
}