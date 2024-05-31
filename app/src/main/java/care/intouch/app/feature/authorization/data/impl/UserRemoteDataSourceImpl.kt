package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.UserApiService
import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper
import care.intouch.app.feature.authorization.data.models.response.UserResponse
import care.intouch.app.feature.common.data.models.exception.NetworkException
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val networkToUserExceptionMapper: NetworkToUserExceptionMapper
) : UserRemoteDataSource {
    override suspend fun getUser(): UserResponse {
        return try {
            userApiService.getUser().first()
        } catch (exception: NetworkException) {
            throw networkToUserExceptionMapper.handleException(exception)
        }
    }
}