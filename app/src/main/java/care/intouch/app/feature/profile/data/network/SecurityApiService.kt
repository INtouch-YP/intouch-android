package care.intouch.app.feature.profile.data.network

import care.intouch.app.feature.profile.data.dto.PasswordDataDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SecurityApiService {

    @GET("/api/v1/user/delete")
    suspend fun deleteProfile()

    @POST("/api/v1/user/update/password/")
    suspend fun updatePassword(
        @Body data: PasswordDataDto
    )
}