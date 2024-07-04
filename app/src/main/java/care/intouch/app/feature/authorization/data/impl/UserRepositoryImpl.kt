package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.authorization.data.models.mappers.UserToDomainMapper
import care.intouch.app.feature.authorization.data.models.response.PasswordResetResponse
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userToDomainMapper: UserToDomainMapper,
    private val exceptionToErrorMapper: UserExceptionToErrorMapper

) : UserRepository {
    override suspend fun getUser(): Resource<User, ErrorEntity> {
        try {
            val response = remoteDataSource.getUser()
            return Resource.Success(userToDomainMapper.toUser(response))

        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun resetPassword(email: String): Result<PasswordResetResponse> {
        return remoteDataSource.resetPassword(email)
    }
}