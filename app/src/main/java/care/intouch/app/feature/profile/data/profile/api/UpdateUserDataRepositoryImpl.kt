package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.UpdateUserDataResponse
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateUserDataRepositoryImpl @Inject constructor(
    private val updateUserDataApi: UpdateUserDataApi
) : UpdateUserDataRepository {
    override suspend fun redactUserData(userData: ProfileData, id: Int): UpdateUserDataResponse {
        return withContext(Dispatchers.IO) {
            try {
                val ttt = formatToQueryMap(userData)
                val response = updateUserDataApi.updateUserData(id, formatToQueryMap(userData))
                UpdateUserDataResponse.UpdateUserDataSuccess(
                    ProfileData(
                        name = response.firstName,
                        lastName = response.lastName
                    )
                )
            } catch (e: Exception) {
                UpdateUserDataResponse.UpdateUserDataError()
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