package care.intouch.app.feature.profile.data.profile.api

import retrofit2.http.POST
import retrofit2.http.Query

interface RedactUserEmailApi {
    @POST("/api/v1/user/update/email/")
    suspend fun updateUserEmail(
        @Query("new_email")  newEmail: String
    ): String
}