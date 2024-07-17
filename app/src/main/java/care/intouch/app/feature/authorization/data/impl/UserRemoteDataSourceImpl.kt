package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.UserApiService
import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.api.UserUtilsApiService
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper
import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.authorization.data.models.request.PasswordResetRequest
import care.intouch.app.feature.authorization.data.models.response.PasswordResetResponse
import care.intouch.app.feature.common.data.models.exception.NetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val userUtilsApiService: UserUtilsApiService,
    private val networkToUserExceptionMapper: NetworkToUserExceptionMapper
) : UserRemoteDataSource {
    override suspend fun getUser(): UserResponse {
        return try {
            userApiService.getUser().first()
        } catch (exception: NetworkException) {
            throw networkToUserExceptionMapper.handleException(exception)
        }
    }

    override suspend fun resetPassword(email: String): Result<PasswordResetResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = userUtilsApiService.resetPassword(PasswordResetRequest(email))
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}