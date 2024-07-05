package care.intouch.app.feature.profile.data.profile.api

import android.util.Log
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.RedactUserDataResponse
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataPatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedactUserDataPatchRepositoryImpl @Inject constructor(
    private val redactUserDataApi: RedactUserDataApi
) : RedactUserDataPatchRepository {
    override suspend fun redactUserData(userData: ProfileData, id: Int): RedactUserDataResponse {
        Log.d("INTOUCH_MY_TAG", "RedactUserDataPatchRepository_IN")
        return withContext(Dispatchers.IO) {
            Log.d("INTOUCH_MY_TAG", "RedactUserDataPatchRepository_IN_THREAD")
            try {
                val ttt = formatToQueryMap(userData)
                val response = redactUserDataApi.updateUserData(id, formatToQueryMap(userData))
                Log.d("INTOUCH_MY_TAG", "RedactUserDataPatchRepository_${response.firstName} ${ttt}")
                RedactUserDataResponse.RedactUserDataSuccess(
                    ProfileData(
                        name = response.firstName,
                        lastName = response.lastName
                    )
                )
            } catch (e: Exception) {
                Log.d("INTOUCH_MY_TAG", "RedactUserDataPatchRepository_Exception")
                RedactUserDataResponse.RedactUserDataError()
            }
        }
    }

    private fun formatToQueryMap(userData: ProfileData): HashMap<String, String> {
        val queryParameters: HashMap<String, String> = HashMap()
        queryParameters["first_name"] = userData.name
        queryParameters["last_name"] = userData.lastName
        return queryParameters
    }
}