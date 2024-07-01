package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.data.profile.models.RedactUserDataResponse
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RedactUserDataApi {
    @PATCH("/api/v1/user/update/{id}/")
    suspend fun updateUserData(
        @Path("id") id: Int,
        @QueryMap userData: HashMap<String, String>
    ): RedactUserDataResponse
}