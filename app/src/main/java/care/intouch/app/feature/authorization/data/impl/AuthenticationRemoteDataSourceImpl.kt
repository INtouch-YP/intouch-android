package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.AuthenticationApiService
import care.intouch.app.feature.authorization.data.api.AuthenticationRemoteDataSource
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToAuthenticationExceptionMapper
import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import care.intouch.app.feature.common.data.models.exception.NetworkException
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val apiService: AuthenticationApiService,
    private val networkToAuthenticationExceptionMapper: NetworkToAuthenticationExceptionMapper
) :
    AuthenticationRemoteDataSource {
    override suspend fun confirmEmail(id: Int, token: String): ConfirmEmailResponse {
        return try {
            apiService.confirmEmail(id, token)
        } catch (exception: NetworkException) {
            throw networkToAuthenticationExceptionMapper.handleException(exception)
        }
    }
}