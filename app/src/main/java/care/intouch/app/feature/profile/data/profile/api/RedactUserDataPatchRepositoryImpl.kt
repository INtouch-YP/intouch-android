package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.data.profile.models.RedactUserDataResponse
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataPatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RedactUserDataPatchRepositoryImpl(
    private val redactUserDataApi: RedactUserDataApi
):
    RedactUserDataPatchRepository {
    override suspend fun redactUserData(userData: ProfileData, id: Int): RedactUserDataResponse {
        return withContext(Dispatchers.IO){
            try {
                val response = redactUserDataApi.updateUserData(id, formatToQueryMap(userData))
                response
            } catch (e: Exception) {
                RedactUserDataResponse("string", "string", "user@example.com", "2024-07-01", "string")
            }
        }
    }

    private fun formatToQueryMap(userData: ProfileData): HashMap<String, String> {
        val queryParameters: HashMap<String, String> = HashMap()
        queryParameters["first_name"] = userData.name
        queryParameters["last_name"] = userData.lastName
        queryParameters["email"] = userData.email
        return queryParameters
    }
}