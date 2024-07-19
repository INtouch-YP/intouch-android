package care.intouch.app.feature.plan.data.network

import care.intouch.app.feature.plan.data.network.dto.response.AssignmentsResponse
import retrofit2.http.GET

interface AssignmentsApiService {

    @GET("/api/v1/assignments-client/")
    suspend fun getAssignments(): AssignmentsResponse
}