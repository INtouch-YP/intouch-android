package care.intouch.app.feature.profile.data.api

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface DeleteProfileRepository {
    suspend fun deleteProfile(): Resource<Unit, ErrorEntity>
}