package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.data.profile.models.UserDataResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface RedactUserDataApi {
    @PATCH("/api/v1/user/update/{id}/")
    suspend fun updateUserData(
        @Path("id") id: Int,
        @Body userData: HashMap<String, String>
    ): UserDataResponse
}