package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.authorization.data.models.response.PasswordResetResponse

interface UserRemoteDataSource {
    suspend fun getUser(): UserResponse
    suspend fun resetPassword(email: String): Result<PasswordResetResponse>
}