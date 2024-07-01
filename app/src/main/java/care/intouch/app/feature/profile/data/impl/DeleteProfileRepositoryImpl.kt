package care.intouch.app.feature.profile.data.impl

import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.profile.data.api.DeleteProfileRepository
import care.intouch.app.feature.profile.data.network.SecurityApiService
import javax.inject.Inject

class DeleteProfileRepositoryImpl @Inject constructor(
    private val apiService: SecurityApiService,
    private val exceptionToErrorMapper: UserExceptionToErrorMapper
): DeleteProfileRepository {
    override suspend fun deleteProfile(): Resource<Unit, ErrorEntity> {
        return try {
            val response = apiService.deleteProfile()
            Resource.Success(response)
        } catch (exception: Exception) {
            val error = exceptionToErrorMapper.handleException(exception)
            return Resource.Error(error)
        }
    }
}