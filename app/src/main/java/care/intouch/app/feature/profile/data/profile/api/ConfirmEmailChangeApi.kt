package care.intouch.app.feature.profile.data.profile.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ConfirmEmailChangeApi {
    @GET("/api/v1/user/update/email/confirm/{id}/{token}/")
    suspend fun confirmEmailChange(
        @Path("id") id: String,
        @Path("id") token: String,
    )
}