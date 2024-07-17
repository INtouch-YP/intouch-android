package care.intouch.app.feature.authorization.data.impl

import android.content.SharedPreferences
import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.authorization.domain.models.User
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserStorageImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val json: Json
) : UserStorage {

    override suspend fun save(user: User): Boolean {
        val mutex = Mutex()
        mutex.withLock {
            val data = json.encodeToString(user)
            return sharedPreferences.edit().putString(KEY, data).commit()
        }
    }

    override suspend fun read(): User? {
        return sharedPreferences.getString(KEY, null)?.let {
            json.decodeFromString<User>(it)
        }
    }

    override fun clear(): Boolean {
        return sharedPreferences.edit().remove(KEY).commit()
    }

    private companion object {
        const val KEY = "user"
    }
}