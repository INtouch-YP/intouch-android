package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.AuthenticationRemoteDataSource
import care.intouch.app.feature.authorization.data.models.mappers.AuthenticationExceptionToErrorMapper
import care.intouch.app.feature.authorization.data.models.mappers.AuthenticationToDomainMapper
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.authorization.domain.models.Authentication
import care.intouch.app.feature.authorization.domain.models.AuthenticationToken
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource,
    private val domainMapper: AuthenticationToDomainMapper,
    private val exceptionToErrorMapper: AuthenticationExceptionToErrorMapper
) : AuthenticationRepository {
    override suspend fun confirmEmail(
        id: Int,
        code: String
    ): Resource<Authentication, ErrorEntity> {
        return try {
            val response = remoteDataSource.confirmEmail(id, code)
            Resource.Success(domainMapper.toAuthenticationOutputData(response))

        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun getToken(
        username: String,
        password: String,
    ): Resource<AuthenticationToken, ErrorEntity> {
        return try {
            val response = remoteDataSource.getToken(username, password)
            Resource.Success(domainMapper.toAuthenticationTokenOutputData(response))
        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }
}