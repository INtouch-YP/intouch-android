package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.authorization.data.models.request.PasswordResetRequest
import care.intouch.app.feature.authorization.data.models.response.PasswordResetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @GET("/api/v1/get-user")
    suspend fun getUser(): List<UserResponse>

}