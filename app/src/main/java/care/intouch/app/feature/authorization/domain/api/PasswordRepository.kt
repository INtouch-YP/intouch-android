package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.domain.dto.NewPasswordModel
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface PasswordRepository {
    suspend fun setPassword(passwords: NewPasswordModel): Resource<Unit, ErrorEntity>
}