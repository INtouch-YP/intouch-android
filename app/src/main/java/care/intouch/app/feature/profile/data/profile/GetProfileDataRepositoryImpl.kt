package care.intouch.app.feature.profile.data.profile

import android.content.SharedPreferences
import care.intouch.app.feature.authorization.data.impl.AccountLocalDataSourceImpl.Companion.KEY
import care.intouch.app.feature.authorization.domain.dto.AccountModel
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.repository.GetProfileDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetProfileDataRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val coroutineScope: CoroutineScope,
    private val json: Json,
): GetProfileDataRepository {
    override suspend fun getProfileDataFromSharedPreferences(): ProfileData? {

        return withContext(Dispatchers.IO) {
            sharedPreferences.getString(PROFILE, null)?.let {
                json.decodeFromString<ProfileData>(it)
            }
        }

    }


    companion object {
        const val PROFILE = "PROFILE"
    }
}