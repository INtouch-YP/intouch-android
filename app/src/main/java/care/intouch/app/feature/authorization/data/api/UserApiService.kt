package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.UserResponse
import retrofit2.http.GET

interface UserApiService {
    @GET("/api/v1/get-user")
    suspend fun getUser(): List<UserResponse>
}