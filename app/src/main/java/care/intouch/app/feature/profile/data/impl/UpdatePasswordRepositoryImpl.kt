package care.intouch.app.feature.profile.data.impl

import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.profile.data.api.UpdatePasswordRepository
import care.intouch.app.feature.profile.data.mappers.toData
import care.intouch.app.feature.profile.data.network.SecurityApiService
import care.intouch.app.feature.profile.domain.models.PasswordData
import javax.inject.Inject

class UpdatePasswordRepositoryImpl @Inject constructor(
    private val apiService: SecurityApiService,
    private val exceptionToErrorMapper: UserExceptionToErrorMapper
): UpdatePasswordRepository {
    override suspend fun updatePassword(data: PasswordData): Resource<Unit, ErrorEntity> {
        return try {
            val response = apiService.updatePassword(data.toData())
            Resource.Success(response)
        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }
}