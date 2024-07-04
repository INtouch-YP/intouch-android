package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.SetPasswordApiService
import care.intouch.app.feature.authorization.data.models.mapToRequest
import care.intouch.app.feature.authorization.data.models.mappers.AuthenticationExceptionToErrorMapper
import care.intouch.app.feature.authorization.domain.api.PasswordRepository
import care.intouch.app.feature.authorization.domain.dto.NewPasswordModel
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val apiDev: SetPasswordApiService,
    private val exceptionToErrorMapper: AuthenticationExceptionToErrorMapper
): PasswordRepository {
    override suspend fun setPassword(passwords: NewPasswordModel): Resource<Unit, ErrorEntity> {
        return try {
            apiDev.setPassword(passwords.mapToRequest())
            Resource.Success(Unit)
        } catch (ex: Exception) {
            val error = exceptionToErrorMapper.handleException(ex)
            return Resource.Error(error)
        }
    }
}