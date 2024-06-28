package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import retrofit2.http.PATCH
import retrofit2.http.Path

interface RedactUserData {
    @PATCH("/api/v1/user/update/{id}/")
    suspend fun updateUserData(
        @Path("id") id: Int,
        profileData: ProfileData
    )
}