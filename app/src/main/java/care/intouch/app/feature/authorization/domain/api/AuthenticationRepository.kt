package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.domain.models.Authentication
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface AuthenticationRepository {
    suspend fun confirmEmail(
        id: Int,
        code: String
    ): Resource<Authentication, ErrorEntity>
}