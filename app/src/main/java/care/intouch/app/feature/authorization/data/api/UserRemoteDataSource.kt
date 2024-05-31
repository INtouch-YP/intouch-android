package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.data.models.response.UserResponse

interface UserRemoteDataSource {
    suspend fun getUser(): UserResponse
}