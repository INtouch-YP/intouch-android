package care.intouch.app.feature.home.data.api

import care.intouch.app.feature.home.data.models.AssignmentsResponse
import care.intouch.app.feature.home.data.models.RegularResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AssignmentsApi {
    @GET("/api/v1/assignments-client/")
    suspend fun getClientsAssignments(@QueryMap request: Map<String, String>): AssignmentsResponse

    @PATCH("/api/v1/assignments-client/{id}/clear/")
    suspend fun clearAssignment(@Path("id") id: Int): RegularResponse

    @PATCH("/api/v1/assignments-client/{id}/visible/")
    suspend fun setAssignmentVisible(@Path("id") id: Int): RegularResponse
}