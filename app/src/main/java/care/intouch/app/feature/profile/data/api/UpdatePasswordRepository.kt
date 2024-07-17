package care.intouch.app.feature.profile.data.api

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.profile.domain.models.PasswordData

interface UpdatePasswordRepository {
    suspend fun updatePassword(data: PasswordData): Resource<Unit, ErrorEntity>
}