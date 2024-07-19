package care.intouch.app.feature.plan.data.network

import care.intouch.app.feature.plan.data.network.dto.Request
import care.intouch.app.feature.plan.data.network.dto.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkAssignmentsSourceImpl(
    private val apiService: AssignmentsApiService,
) : NetworkAssignmentsSource {
    override suspend fun doRequest(request: Request): Response {

        return withContext(Dispatchers.IO) {
            when(request) {
                Request.AssignmentsRequest -> getAssignments()
            }
        }
    }

    private suspend fun getAssignments(): Response {
        return try {
            apiService.getAssignments()
        } catch (exception: Exception) {
            throw exception
        }
    }
}