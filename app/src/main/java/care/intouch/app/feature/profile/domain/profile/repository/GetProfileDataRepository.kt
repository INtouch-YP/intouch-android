package care.intouch.app.feature.profile.domain.profile.repository

import care.intouch.app.feature.profile.domain.profile.models.ProfileData

interface GetProfileDataRepository {
    suspend fun getProfileDataFromSharedPreferences(): ProfileData?
}