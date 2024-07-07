package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.domain.profile.models.UpdateUserEmailResponse
import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UpdateUserEmailApi {
    @POST("/api/v1/user/update/email/")
    suspend fun updateUserEmail(
        @Body newEmail: UpdateUserEmailRequest
    ): UpdateUserEmailResponse
}