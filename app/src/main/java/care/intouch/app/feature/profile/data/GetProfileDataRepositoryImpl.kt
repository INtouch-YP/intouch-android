package care.intouch.app.feature.profile.data

import android.content.SharedPreferences
import care.intouch.app.feature.profile.domain.models.ProfileData
import care.intouch.app.feature.profile.domain.repository.GetProfileDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import javax.inject.Inject

class GetProfileDataRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val coroutineScope: CoroutineScope,
    private val json: Json,
): GetProfileDataRepository {
    override suspend fun getProfileDataFromSharedPreferences(): ProfileData {
        TODO("Not yet implemented")
    }

}