package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.domain.models.User

interface UserStorage {
    fun save(user: User): Boolean
    fun raed(): User?
    fun clear(): Boolean
}