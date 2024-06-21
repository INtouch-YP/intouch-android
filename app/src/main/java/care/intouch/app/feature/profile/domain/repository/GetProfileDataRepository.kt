package care.intouch.app.feature.profile.domain.repository

import care.intouch.app.feature.profile.domain.models.ProfileData

interface GetProfileDataRepository {
    suspend fun getProfileDataFromSharedPreferences(): ProfileData
}