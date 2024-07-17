package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.domain.models.User

interface UserStorage {
    suspend fun save(user: User): Boolean
    suspend fun read(): User?
    fun clear(): Boolean
}