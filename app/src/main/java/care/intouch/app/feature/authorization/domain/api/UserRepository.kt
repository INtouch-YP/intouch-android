package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface UserRepository {
    suspend fun getUser(): Resource<User, ErrorEntity>
}